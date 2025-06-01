import React, { useEffect, useState } from "react";
import Sidebar from "./Sidebar";
import DeviceList from "./DeviceList"; // assuming it's in the same folder
import "./Dashboard.css";
import axios from "axios";

const Dashboard = () => {
  const [patients, setPatients] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/v1/patients")
      .then((res) => setPatients(res.data))
      .catch((err) => console.error("Error fetching patients:", err));
  }, []);

  return (
    <div className="dashboard-container">
      <Sidebar />

      <div className="dashboard-main">
        {/* Patient List */}
        <div className="section patient-list">
          <h3>Patient List</h3>
          <table>
            <thead>
              <tr>
                <th>Name</th>
                <th>Age</th>
                <th>Status</th>
              </tr>
            </thead>
            <tbody>
              {patients.map((patient) => (
                <tr key={patient.id}>
                  <td>{patient.name}</td>
                  <td>{patient.age}</td>
                  <td
                    style={{
                      color: patient.status === "Critical" ? "red" : "black",
                    }}
                  >
                    {patient.status}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>

        {/* Device List Section */}
        <div className="section device-list">
          <h3>Device List</h3>
          <DeviceList />
        </div>

        {/* Placeholder for vitals */}
        <div className="section vitals-chart">
          <h3>Vitals Chart</h3>
          <div className="chart-placeholder">📈 Chart goes here</div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
