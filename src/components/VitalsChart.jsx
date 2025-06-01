import React, { useEffect, useState } from "react";
import axios from "axios";

const VitalsChart = () => {
  const [vitals, setVitals] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/api/vitals")
      .then(response => setVitals(response.data))
      .catch(error => console.error("Error fetching vitals:", error));
  }, []);

  return (
    <div>
      <h2>Vitals Chart</h2>
      <ul>
        {vitals.map(vital => (
          <li key={vital.id}>
            {vital.type}: {vital.value} at {new Date(vital.timestamp).toLocaleString()}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default VitalsChart;
