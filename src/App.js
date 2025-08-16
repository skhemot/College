import React from "react";
import { Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import HomePage from "./components/HomePage";
import StudentPage from "./components/StudentPage";
import TeacherPage from "./components/TeacherPage";
import "./styles/App.css";

export default function App() {
  return (
    <>
      <Navbar />
      <main className="container">
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/students" element={<StudentPage />} />
          <Route path="/teachers" element={<TeacherPage />} />
        </Routes>
      </main>
      <footer className="footer">
        © {new Date().getFullYear()} College Admin
      </footer>
    </>
  );
}
