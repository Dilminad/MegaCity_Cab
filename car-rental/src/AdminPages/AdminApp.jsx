import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import DashboardStats from "./Dashboard/DashboardStats";
import RidesPage from "./Dashboard/Pages/RidesPage";
import VehiclesPage from "./Dashboard/Pages/VehiclesPage";
import SettingsPage from "./Dashboard/Pages/SettingsPage";
import Sidebar from "./Dashboard/Sidebar";
import Header from "./Dashboard/Header";
import Profile from "./Dashboard/Pages/Profile";

function Dashboard() {
  return (
    <main className="p-6 mt-16">
      <div className="mb-6">
        <h1 className="text-2xl font-semibold">Dashboard Overview</h1>
        <p className="text-gray-600">
          Welcome back to your cab service admin panel
        </p>
      </div>
      <DashboardStats />
    </main>
  );
}

export function AdminApp() {
  return (
    <Router>
      <div className="flex min-h-screen bg-gray-50">
        <Sidebar />
        <div className="flex-1 ml-64">
          <Header />
          <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="/rides" element={<RidesPage />} />
            <Route path="/drivers" element={<RidesPage />} />
            <Route path="/vehicles" element={<VehiclesPage />} />
            <Route path="/settings" element={<SettingsPage />} />
            <Route path="/profile-settings" element={<Profile />} /> {/* Fixed Route */}
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default AdminApp;
