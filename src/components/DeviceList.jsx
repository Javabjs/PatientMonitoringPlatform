import React, { useEffect, useState } from "react";
import axios from "axios";

const DeviceList = () => {
  const [devices, setDevices] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/api/v1/devices") // Make sure this matches your backend route
      .then(response => {
        console.log("Devices fetched from backend:", response.data);
        setDevices(response.data);
      })
      .catch(error => console.error("Error fetching devices:", error));
  }, []);

  return (
    <div>
      <h2>Device List</h2>
      <table>
        <thead>
          <tr>
            <th>Device Name</th>
            <th>MAC Address</th>
            <th>Status</th>
            <th>Power</th>
            <th>Type</th>
          </tr>
        </thead>
        <tbody>
          {devices.map(device => (
            <tr key={device.id}>
              <td>{device.deviceName}</td>
              <td>{device.macAddress}</td>
              <td>{device.deviceState}</td>
              <td>{device.powerMode}</td>
              <td>{device.deviceType}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default DeviceList;
