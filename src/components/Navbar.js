import React from "react";
import { NavLink } from "react-router-dom";
import "../styles/Navbar.css";

export default function Navbar() {
  return (
    <header className="nav">
      <div className="brand">College Admin</div>
      <nav>
        <NavLink to="/" end className="nav-link">Home</NavLink>
        <NavLink to="/students" className="nav-link">Student</NavLink>
        <NavLink to="/teachers" className="nav-link">Teacher</NavLink>
      </nav>
    </header>
  );
}
