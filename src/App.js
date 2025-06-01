import Signup from "./components/Signup";
import Login from "./components/Login";
import { Routes, Route, Navigate } from "react-router-dom";
import UserList from "./components/userList"; 
import Dashboard from "./components/Dashboard"; 
import PatientList from "./components/PatientList";
import DeviceList from "./components/DeviceList";
import VitalsChart from "./components/VitalsChart";
import HealthPredictor from "./components/HealthPredictor";

function App() {
  return (
    <>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/users" element={<UserList />} />

        {/* Dashboard with nested routes */}
        <Route path="/dashboard" element={<Dashboard />}>
          <Route index element={<PatientList />} /> {/* default route */}
          <Route path="patients" element={<PatientList />} />
          <Route path="devices" element={<DeviceList />} />
          <Route path="vitals" element={<VitalsChart />} />
          <Route path="predict" element={<HealthPredictor />} />
        </Route>

        <Route path="/" element={<Navigate to="/login" replace />} />
      </Routes>
    </>
  );
}

export default App;
