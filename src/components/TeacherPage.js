import React, { useEffect, useMemo, useState } from "react";
import { API_BASE } from "../config";
import "../styles/StudentPage.css";

export default function TeacherPage() {
  const [list, setList] = useState([]);
  const [query, setQuery] = useState("");
  const [sortKey, setSortKey] = useState("id");
  const [loading, setLoading] = useState(false);
  const [modalOpen, setModalOpen] = useState(false);
  const [form, setForm] = useState({ id: "", name: "", age: "", address: "" });

  const fetchAll = async () => {
    setLoading(true);
    try {
      const res = await fetch(`${API_BASE}/getAllTeacher`);
      const data = await res.json();
      setList(data || []);
    } catch {
      alert("Failed to fetch teachers");
    } finally {
      setLoading(false);
    }
  };

  const openCreate = () => {
    setForm({ id: "", name: "", age: "", address: "" });
    setModalOpen(true);
  };

  const openEdit = (row) => {
    setForm({ ...row });
    setModalOpen(true);
  };

  const save = async () => {
    const payload = { ...form, age: Number(form.age) };
    const isUpdate = !!form.id;
    try {
      const res = await fetch(
        isUpdate ? `${API_BASE}/updateTeacher/${form.id}` : `${API_BASE}/createTeacher`,
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
      alert("Failed to save teacher");
    }
  };

  const remove = async (id) => {
    if (!window.confirm(`Delete teacher #${id}?`)) return;
    try {
      const res = await fetch(`${API_BASE}/deleteTeacher/${id}`, { method: "DELETE" });
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
          (t) =>
            String(t.id).includes(q) ||
            (t.name || "").toLowerCase().includes(q)
        );
    const sorted = [...base].sort((a, b) => {
      if (sortKey === "name") return (a.name || "").localeCompare(b.name || "");
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
        <h2>Teachers</h2>
        <div className="actions">
          <input className="input" placeholder="Search by id, name..." value={query} onChange={(e) => setQuery(e.target.value)} />
          <select className="select" value={sortKey} onChange={(e) => setSortKey(e.target.value)}>
            <option value="id">Sort: ID</option>
            <option value="name">Sort: Name</option>
            <option value="age">Sort: Age</option>
          </select>
          <button className="btn primary" onClick={openCreate}>+ Add Teacher</button>
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
                <th>ID</th><th>Name</th><th>Age</th><th>Address</th><th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {filtered.map((t) => (
                <tr key={t.id}>
                  <td>{t.id}</td>
                  <td>{t.name}</td>
                  <td>{t.age}</td>
                  <td>{t.address}</td>
                  <td className="row-actions">
                    <button className="btn small" onClick={() => openEdit(t)}>Edit</button>
                    <button className="btn small danger" onClick={() => remove(t.id)}>Delete</button>
                  </td>
                </tr>
              ))}
              {filtered.length === 0 && (
                <tr><td colSpan="5" className="empty">No teachers found.</td></tr>
              )}
            </tbody>
          </table>
        )}
      </div>

      {/* Modal */}
      {modalOpen && (
        <div className="modal-backdrop" onClick={() => setModalOpen(false)}>
          <div className="modal" onClick={(e) => e.stopPropagation()}>
            <h3>{form.id ? "Update Teacher" : "Add Teacher"}</h3>
            <div className="grid">
              <input className="input" placeholder="Name" value={form.name} onChange={(e) => setForm({ ...form, name: e.target.value })} />
              <input className="input" type="number" placeholder="Age" value={form.age} onChange={(e) => setForm({ ...form, age: e.target.value })} />
              <input className="input" placeholder="Address" value={form.address} onChange={(e) => setForm({ ...form, address: e.target.value })} />
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
