import React from "react";
import "./Sidebar.css";

const Sidebar = () => {
  return (
    <div className="sidebar">
      <h2 className="sidebar-title">PATIENT MONITORING</h2>
      <ul className="sidebar-menu">
        <li>📊 Dashboard</li>
        <li>🧑‍⚕️ Patients</li>
        <li>🚨 Alerts</li>
        <li>⚙️ Admin</li>
      </ul>
    </div>
  );
};

export default Sidebar;
