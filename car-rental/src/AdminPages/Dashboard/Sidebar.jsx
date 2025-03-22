import React, { useState, useEffect } from "react";
import { Link, useNavigate, useLocation } from "react-router-dom";
import {
  LayoutDashboard,
  Car,
  Users,
  Settings,
  LogOut,
  Box,
  ChevronDown,
  ChevronUp,
  Bell,
  Menu,
  Folder,
  BarChart, // Add this import for Charts
} from "lucide-react";

const Sidebar = () => {
  const [expandDrivers, setExpandDrivers] = useState(false);
  const [expandVehicles, setExpandVehicles] = useState(false);
  const [expandCategories, setExpandCategories] = useState(false);
  const [expandAdmins, setExpandAdmins] = useState(false);
  const [isCollapsed, setIsCollapsed] = useState(false);
  const [isLoggingOut, setIsLoggingOut] = useState(false);
  const navigate = useNavigate();
  const location = useLocation();

  const handleLogout = () => {
    setIsLoggingOut(true);
    localStorage.removeItem("userToken");
    sessionStorage.clear();
    setTimeout(() => {
      navigate("/", { replace: true });
      setIsLoggingOut(false);
    }, 500);
  };

  const isActive = (path) => location.pathname === path;

  useEffect(() => {
    const handleClickOutside = (event) => {
      if (isCollapsed && !event.target.closest(".sidebar")) {
        setIsCollapsed(false);
      }
    };
    document.addEventListener("mousedown", handleClickOutside);
    return () => document.removeEventListener("mousedown", handleClickOutside);
  }, [isCollapsed]);

  return (
    <div
      className={`w-64 h-screen bg-gray-800 text-white p-4 fixed left-0 top-0 transition-all duration-300 z-20 ${
        isCollapsed ? "w-16" : ""
      } sidebar`}
    >
      <button
        onClick={() => setIsCollapsed(!isCollapsed)}
        className="mb-4 p-2 rounded-lg hover:bg-gray-700 focus:outline-none"
      >
        <Menu size={20} />
      </button>

      {!isCollapsed && (
        <div className="mb-8">
          <h1 className="text-xl font-bold">CabAdmin</h1>
        </div>
      )}

      <nav>
        <Link
          to="/admin/dashboard"
          className={`flex items-center space-x-3 p-3 rounded-lg hover:bg-gray-700 cursor-pointer mb-2 ${
            isActive("/admin/dashboard") ? "bg-gray-700" : ""
          }`}
        >
          <LayoutDashboard size={20} />
          {!isCollapsed && <span>Dashboard</span>}
        </Link>

        <Link
          to="/admin/rides"
          className={`flex items-center space-x-3 p-3 rounded-lg hover:bg-gray-700 cursor-pointer mb-2 ${
            isActive("/admin/rides") ? "bg-gray-700" : ""
          }`}
        >
          <Box size={20} />
          {!isCollapsed && <span>Rides</span>}
        </Link>

        {/* Drivers Section */}
        <div
          className={`flex items-center justify-between p-3 rounded-lg hover:bg-gray-700 cursor-pointer mb-2 ${
            isActive("/admin/manage-drivers") || isActive("/admin/driver-requests")
              ? "bg-gray-700"
              : ""
          }`}
          onClick={() => setExpandDrivers(!expandDrivers)}
        >
          <div className="flex items-center space-x-3">
            <Users size={20} />
            {!isCollapsed && <span>Drivers</span>}
          </div>
          {!isCollapsed && (expandDrivers ? <ChevronUp size={18} /> : <ChevronDown size={18} />)}
        </div>
        {expandDrivers && !isCollapsed && (
          <div className="ml-6">
            <Link
              to="/admin/manage-drivers"
              className={`block p-2 rounded-lg hover:bg-gray-700 cursor-pointer mb-1 ${
                isActive("/admin/manage-drivers") ? "bg-gray-700" : ""
              }`}
            >
              Manage Drivers
            </Link>
            <Link
              to="/admin/driver-requests"
              className={`block p-2 rounded-lg hover:bg-gray-700 cursor-pointer ${
                isActive("/admin/driver-requests") ? "bg-gray-700" : ""
              }`}
            >
              Driver Requests
            </Link>
          </div>
        )}

        {/* Vehicles Section */}
        <div
          className={`flex items-center justify-between p-3 rounded-lg hover:bg-gray-700 cursor-pointer mb-2 ${
            isActive("/admin/manage-vehicles") || isActive("/admin/vehicle-requests")
              ? "bg-gray-700"
              : ""
          }`}
          onClick={() => setExpandVehicles(!expandVehicles)}
        >
          <div className="flex items-center space-x-3">
            <Car size={20} />
            {!isCollapsed && <span>Vehicles</span>}
          </div>
          {!isCollapsed && (expandVehicles ? <ChevronUp size={18} /> : <ChevronDown size={18} />)}
        </div>
        {expandVehicles && !isCollapsed && (
          <div className="ml-6">
            <Link
              to="/admin/manage-vehicles"
              className={`block p-2 rounded-lg hover:bg-gray-700 cursor-pointer mb-1 ${
                isActive("/admin/manage-vehicles") ? "bg-gray-700" : ""
              }`}
            >
              Manage Vehicles
            </Link>
            <Link
              to="/admin/vehicle-requests"
              className={`block p-2 rounded-lg hover:bg-gray-700 cursor-pointer ${
                isActive("/admin/vehicle-requests") ? "bg-gray-700" : ""
              }`}
            >
              Vehicle Requests
            </Link>
          </div>
        )}

        {/* Categories Section */}
        <div
          className={`flex items-center justify-between p-3 rounded-lg hover:bg-gray-700 cursor-pointer mb-2 ${
            isActive("/admin/manage-categories") || isActive("/admin/add-category")
              ? "bg-gray-700"
              : ""
          }`}
          onClick={() => setExpandCategories(!expandCategories)}
        >
          <div className="flex items-center space-x-3">
            <Folder size={20} />
            {!isCollapsed && <span>Categories</span>}
          </div>
          {!isCollapsed && (expandCategories ? <ChevronUp size={18} /> : <ChevronDown size={18} />)}
        </div>
        {expandCategories && !isCollapsed && (
          <div className="ml-6">
            <Link
              to="/admin/manage-categories"
              className={`block p-2 rounded-lg hover:bg-gray-700 cursor-pointer mb-1 ${
                isActive("/admin/manage-categories") ? "bg-gray-700" : ""
              }`}
            >
              Manage Categories
            </Link>
            <Link
              to="/admin/add-category"
              className={`block p-2 rounded-lg hover:bg-gray-700 cursor-pointer ${
                isActive("/admin/add-category") ? "bg-gray-700" : ""
              }`}
            >
              Add Category
            </Link>
          </div>
        )}

        {/* Admins Section */}
        <div
          className={`flex items-center justify-between p-3 rounded-lg hover:bg-gray-700 cursor-pointer mb-2 ${
            isActive("/admin/manage-admins") || isActive("/admin/add-admin") ? "bg-gray-700" : ""
          }`}
          onClick={() => setExpandAdmins(!expandAdmins)}
        >
          <div className="flex items-center space-x-3">
            <Users size={20} />
            {!isCollapsed && <span>Admins</span>}
          </div>
          {!isCollapsed && (expandAdmins ? <ChevronUp size={18} /> : <ChevronDown size={18} />)}
        </div>
        {expandAdmins && !isCollapsed && (
          <div className="ml-6">
            <Link
              to="/admin/manage-admins"
              className={`block p-2 rounded-lg hover:bg-gray-700 cursor-pointer mb-1 ${
                isActive("/admin/manage-admins") ? "bg-gray-700" : ""
              }`}
            >
              Manage Admins
            </Link>
            <Link
              to="/admin/add-admin"
              className={`block p-2 rounded-lg hover:bg-gray-700 cursor-pointer ${
                isActive("/admin/add-admin") ? "bg-gray-700" : ""
              }`}
            >
              Add Admin
            </Link>
          </div>
        )}

        {/* Charts Section */}
        <Link
          to="/admin/charts"
          className={`flex items-center space-x-3 p-3 rounded-lg hover:bg-gray-700 cursor-pointer mb-2 ${
            isActive("/admin/charts") ? "bg-gray-700" : ""
          }`}
        >
          <BarChart size={20} />
          {!isCollapsed && <span>Charts</span>}
        </Link>

        {/* Settings Section */}
        <Link
          to="/admin/settings"
          className={`flex items-center space-x-3 p-3 rounded-lg hover:bg-gray-700 cursor-pointer mb-2 ${
            isActive("/admin/settings") ? "bg-gray-700" : ""
          }`}
        >
          <Settings size={20} />
          {!isCollapsed && <span>Settings</span>}
        </Link>

        <Link
          to="/admin/profile"
          className={`flex items-center space-x-3 p-3 rounded-lg hover:bg-gray-700 cursor-pointer mb-2 ${
            isActive("/admin/profile") ? "bg-gray-700" : ""
          }`}
        >
          <Users size={20} />
          {!isCollapsed && <span>Profile</span>}
        </Link>

        <div
          className="relative p-3 mb-2"
          onClick={() => alert("Notifications clicked!")}
        >
          <button className="flex items-center space-x-3">
            <Bell size={20} className="text-gray-400" />
            {!isCollapsed && <span className="text-sm">Notifications</span>}
            <span className="absolute -top-1 -right-1 bg-red-500 text-white text-xs rounded-full w-4 h-4 flex items-center justify-center">
              5
            </span>
          </button>
        </div>
      </nav>

      <div className="absolute bottom-4 left-4 right-4">
        <div
          className={`flex items-center space-x-3 p-3 rounded-lg hover:bg-gray-700 cursor-pointer text-red-400 ${
            isLoggingOut ? "opacity-50 cursor-not-allowed" : ""
          }`}
          onClick={handleLogout}
        >
          <LogOut size={20} />
          {!isCollapsed && <span>{isLoggingOut ? "Logging out..." : "Logout"}</span>}
        </div>
      </div>
    </div>
  );
};

export default Sidebar;