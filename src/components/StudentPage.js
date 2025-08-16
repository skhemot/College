import React, { useEffect, useMemo, useState } from "react";

import { API_BASE } from '../config';

import "../styles/StudentPage.css";

export default function StudentPage() {
  const [list, setList] = useState([]);
  const [query, setQuery] = useState("");
  const [sortKey, setSortKey] = useState("id");
  const [loading, setLoading] = useState(false);
  const [modalOpen, setModalOpen] = useState(false);
  const [form, setForm] = useState({ id: "", name: "", surname: "", age: "", marks: "", result: "" });

  const fetchAll = async () => {
    setLoading(true);
    try {
      const res = await fetch(`${API_BASE}/getAllStudents`);
      const data = await res.json();
      setList(data || []);
    } catch (e) {
      alert("Failed to fetch students");
    } finally {
      setLoading(false);
    }
  };

  const openCreate = () => {
    setForm({ id: "", name: "", surname: "", age: "", marks: "", result: "" });
    setModalOpen(true);
  };

  const openEdit = (row) => {
    setForm({ ...row });
    setModalOpen(true);
  };

  const save = async () => {
    const payload = { ...form, age: Number(form.age), marks: Number(form.marks) };
    const isUpdate = !!form.id;
    try {
      const res = await fetch(
        isUpdate ? `${API_BASE}/updateStudent/${form.id}` : `${API_BASE}/createStudent`,
        {
          method: isUpdate ? "PUT" : "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(payload),
        }
      );
      if (!res.ok) throw new Error("Bad response");
      setModalOpen(false);
      await fetchAll();
    } catch {
      alert("Failed to save student");
    }
  };

  const remove = async (id) => {
    if (!window.confirm(`Delete student #${id}?`)) return;
    try {
      const res = await fetch(`${API_BASE}/deleteStudent/${id}`, { method: "DELETE" });
      if (!res.ok) throw new Error();
      await fetchAll();
    } catch {
      alert("Failed to delete");
    }
  };

  const filtered = useMemo(() => {
    const q = query.trim().toLowerCase();
    const base = !q
      ? list
      : list.filter(
          (s) =>
            String(s.id).includes(q) ||
            (s.name || "").toLowerCase().includes(q) ||
            (s.surname || "").toLowerCase().includes(q)
        );
    const sorted = [...base].sort((a, b) => {
      if (sortKey === "name") return (a.name || "").localeCompare(b.name || "");
      if (sortKey === "marks") return (b.marks ?? 0) - (a.marks ?? 0);
      if (sortKey === "age") return (a.age ?? 0) - (b.age ?? 0);
      return (a.id ?? 0) - (b.id ?? 0);
    });
    return sorted;
  }, [list, query, sortKey]);

  useEffect(() => {
    fetchAll();
  }, []);

  return (
    <section className="card">
      <header className="card-header">
        <h2>Students</h2>
        <div className="actions">
          <input className="input" placeholder="Search by id, name, surname..." value={query} onChange={(e) => setQuery(e.target.value)} />
          <select className="select" value={sortKey} onChange={(e) => setSortKey(e.target.value)}>
            <option value="id">Sort: ID</option>
            <option value="name">Sort: Name</option>
            <option value="age">Sort: Age</option>
            <option value="marks">Sort: Marks</option>
          </select>
          <button className="btn primary" onClick={openCreate}>+ Add Student</button>
          <button className="btn" onClick={fetchAll}>↻ Refresh</button>
        </div>
      </header>

      <div className="table-wrap">
        {loading ? (
          <div className="loader">Loading…</div>
        ) : (
          <table className="table">
            <thead>
              <tr>
                <th>ID</th><th>Name</th><th>Surname</th><th>Age</th><th>Marks</th><th>Result</th><th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {filtered.map((s) => (
                <tr key={s.id}>
                  <td>{s.id}</td>
                  <td>{s.name}</td>
                  <td>{s.surname}</td>
                  <td>{s.age}</td>
                  <td>{s.marks}</td>
                  <td>{s.result}</td>
                  <td className="row-actions">
                    <button className="btn small" onClick={() => openEdit(s)}>Edit</button>
                    <button className="btn small danger" onClick={() => remove(s.id)}>Delete</button>
                  </td>
                </tr>
              ))}
              {filtered.length === 0 && (
                <tr><td colSpan="7" className="empty">No students found.</td></tr>
              )}
            </tbody>
          </table>
        )}
      </div>

      {/* Modal */}
      {modalOpen && (
        <div className="modal-backdrop" onClick={() => setModalOpen(false)}>
          <div className="modal" onClick={(e) => e.stopPropagation()}>
            <h3>{form.id ? "Update Student" : "Add Student"}</h3>
            <div className="grid">
              <input className="input" placeholder="Name" value={form.name} onChange={(e) => setForm({ ...form, name: e.target.value })} />
              <input className="input" placeholder="Surname" value={form.surname} onChange={(e) => setForm({ ...form, surname: e.target.value })} />
              <input className="input" type="number" placeholder="Age" value={form.age} onChange={(e) => setForm({ ...form, age: e.target.value })} />
              <input className="input" type="number" placeholder="Marks" value={form.marks} onChange={(e) => setForm({ ...form, marks: e.target.value })} />
              <input className="input" placeholder="Result" value={form.result} onChange={(e) => setForm({ ...form, result: e.target.value })} />
            </div>
            <div className="modal-actions">
              <button className="btn primary" onClick={save}>Save</button>
              <button className="btn" onClick={() => setModalOpen(false)}>Cancel</button>
            </div>
          </div>
        </div>
      )}
    </section>
  );
}
