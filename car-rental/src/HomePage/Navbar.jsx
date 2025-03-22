import React, { useState, useEffect } from "react";
import { Menu, X, Car } from "lucide-react";
import { motion, AnimatePresence } from "framer-motion";
import { Link } from "react-router-dom";

const Navbar = () => {
  const [isOpen, setIsOpen] = useState(false);
  const [isDropdownOpen, setIsDropdownOpen] = useState(false);
  const [user, setUser] = useState(null); // Holds user data when logged in

  // Simulate checking login state (Replace with actual authentication logic)
  useEffect(() => {
    const loggedInUser = JSON.parse(localStorage.getItem("user")); // Get user from localStorage
    if (loggedInUser) {
      setUser(loggedInUser); // Set user if exists
    }
  }, []);

  const toggleDropdown = () => {
    setIsDropdownOpen(!isDropdownOpen);
  };

  return (
    <nav className="fixed top-0 left-0 w-full bg-white bg-opacity-90 backdrop-blur-md shadow-lg z-50">
      <div className="max-w-7xl mx-auto px-6 py-4 flex justify-between items-center">
        {/* Logo */}
        <div className="flex items-center space-x-2">
          <Car size={32} className="text-yellow-500" />
          <h1 className="text-3xl font-extrabold tracking-wide bg-gradient-to-r from-yellow-500 to-orange-500 bg-clip-text text-transparent">
            Mega City Cab
          </h1>
        </div>

        {/* Navigation Menu */}
        <div className="flex-1 flex justify-center">
          <ul className="hidden md:flex space-x-8 text-lg font-normal font-sans text-gray-900">
            <li><Link to="/" className="hover:text-yellow-500">Home</Link></li>
            <li><Link to="/rides" className="hover:text-yellow-500">Rides</Link></li>
            <li><Link to="/driver" className="hover:text-yellow-500">Drive</Link></li>
            <li><Link to="/vehicle" className="hover:text-yellow-500">Vehicle</Link></li>
            <li><Link to="/about" className="hover:text-yellow-500">About</Link></li>
            <li><Link to="/contact" className="hover:text-yellow-500">Contact</Link></li>
          </ul>
        </div>

        {/* Login Button or User Profile (Right Side) */}
        <div className="flex items-center">
          {user ? (
            <div className="relative">
              <button onClick={toggleDropdown} className="flex items-center space-x-2 focus:outline-none">
                <img
                  src={user.imageUrl}
                  alt="User"
                  className="w-10 h-10 rounded-full border-2 border-yellow-500"
                />
                <span className="text-gray-900">{user.name}</span>
              </button>

              {/* Dropdown Menu */}
              {isDropdownOpen && (
                <div className="absolute right-0 mt-2 w-48 bg-white border border-gray-200 rounded-lg shadow-lg">
                  <Link to="/profile" className="block px-4 py-2 text-gray-700 hover:bg-gray-100">My Profile</Link>
                  <Link to="/settings" className="block px-4 py-2 text-gray-700 hover:bg-gray-100">Settings</Link>
                  <Link to="/help" className="block px-4 py-2 text-gray-700 hover:bg-gray-100">Help</Link>
                  <button onClick={() => { localStorage.removeItem("user"); setUser(null); setIsDropdownOpen(false); }} className="w-full text-left px-4 py-2 text-gray-700 hover:bg-gray-100">
                    Log Out
                  </button>
                </div>
              )}
            </div>
          ) : (
            <Link
              to="/login"
              className="px-4 py-2 border-2 border-yellow-500 text-yellow-500 hover:bg-yellow-500 hover:text-white rounded-lg transition-all duration-300"
            >
              Login
            </Link>
          )}

          {/* Mobile Menu Button */}
          <button
            className="ml-4 md:hidden focus:outline-none transition-all duration-300 hover:scale-110"
            onClick={() => setIsOpen(!isOpen)}
          >
            {isOpen ? <X size={32} className="text-yellow-500" /> : <Menu size={32} className="text-yellow-500" />}
          </button>
        </div>
      </div>

      {/* Mobile Menu */}
      <AnimatePresence>
        {isOpen && (
          <motion.div
            initial={{ opacity: 0, y: -20 }}
            animate={{ opacity: 1, y: 0 }}
            exit={{ opacity: 0, y: -20 }}
            className="md:hidden bg-white text-gray-900 flex flex-col items-center space-y-6 py-6 shadow-lg"
          >
            <Link to="/" onClick={() => setIsOpen(false)} className="text-lg hover:text-yellow-500">Home</Link>
            <Link to="/rides" onClick={() => setIsOpen(false)} className="text-lg hover:text-yellow-500">Rides</Link>
            <Link to="/driver" onClick={() => setIsOpen(false)} className="text-lg hover:text-yellow-500">Drive</Link>
            <Link to="/vehicle" onClick={() => setIsOpen(false)} className="text-lg hover:text-yellow-500">Vehicle</Link>
            <Link to="/about" onClick={() => setIsOpen(false)} className="text-lg hover:text-yellow-500">About</Link>
            <Link to="/contact" onClick={() => setIsOpen(false)} className="text-lg hover:text-yellow-500">Contact</Link>

            {/* Show Login or User Profile in Mobile Menu */}
            {user ? (
              <Link to="/profile" onClick={() => setIsOpen(false)} className="flex items-center space-x-2">
                <img src={user.imageUrl} alt="User" className="w-10 h-10 rounded-full border-2 border-yellow-500" />
                <span className="text-gray-900">{user.name}</span>
              </Link>
            ) : (
              <Link
                to="/login"
                className="px-6 py-2 border-2 border-yellow-500 text-yellow-500 hover:bg-yellow-500 hover:text-white rounded-lg transition-all duration-300"
                onClick={() => setIsOpen(false)}
              >
                Login
              </Link>
            )}
          </motion.div>
        )}
      </AnimatePresence>
    </nav>
  );
};

export default Navbar;