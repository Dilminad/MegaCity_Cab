import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Profile = () => {
  const [admin, setAdmin] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Replace this with the actual admin ID or get it from auth context/props
  const adminId = 'some-admin-id'; // Example: Pass via props or get from a logged-in user session

  useEffect(() => {
    const fetchAdminDetails = async () => {
      try {
        setLoading(true);
        // Adjust the API URL based on your backend setup
        const response = await axios.get(`http://localhost:8080/admin/${adminId}`);
        setAdmin(response.data);
        setLoading(false);
      } catch (err) {
        setError('Failed to fetch admin details. Please try again later.');
        setLoading(false);
      }
    };

    fetchAdminDetails();
  }, [adminId]);

  if (loading) {
    return (
      <div className="flex justify-center items-center h-screen">
        <div className="animate-spin rounded-full h-16 w-16 border-t-4 border-blue-500"></div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="flex justify-center items-center h-screen">
        <div className="text-red-500 text-xl font-semibold">{error}</div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gradient-to-br from-gray-100 to-gray-200 flex justify-center items-center p-4">
      <div className="max-w-2xl w-full bg-white rounded-2xl shadow-xl p-8 transform transition-all duration-300 hover:shadow-2xl">
        <h2 className="text-3xl font-bold text-center text-gray-800 mb-6">Admin Profile</h2>
        {admin && (
          <div className="flex flex-col md:flex-row items-center gap-6">
            {/* Profile Picture */}
            <div className="flex-shrink-0">
              {admin.profilePicture ? (
                <img
                  src={admin.profilePicture}
                  alt="Profile"
                  className="w-32 h-32 rounded-full object-cover border-4 border-blue-500 shadow-md transition-transform duration-300 hover:scale-105"
                />
              ) : (
                <div className="w-32 h-32 rounded-full bg-gray-200 flex items-center justify-center text-gray-500 font-semibold border-4 border-dashed border-gray-300">
                  No Image
                </div>
              )}
            </div>

            {/* Admin Details */}
            <div className="flex-1 space-y-4">
              <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
                <div className="flex flex-col">
                  <span className="text-sm font-medium text-gray-500">First Name</span>
                  <span className="text-lg font-semibold text-gray-800">{admin.firstName}</span>
                </div>
                <div className="flex flex-col">
                  <span className="text-sm font-medium text-gray-500">Last Name</span>
                  <span className="text-lg font-semibold text-gray-800">{admin.lastName}</span>
                </div>
                <div className="flex flex-col">
                  <span className="text-sm font-medium text-gray-500">Username</span>
                  <span className="text-lg font-semibold text-gray-800">{admin.userName}</span>
                </div>
                <div className="flex flex-col">
                  <span className="text-sm font-medium text-gray-500">Email</span>
                  <span className="text-lg font-semibold text-gray-800">{admin.email}</span>
                </div>
                <div className="flex flex-col">
                  <span className="text-sm font-medium text-gray-500">NIC</span>
                  <span className="text-lg font-semibold text-gray-800">{admin.nic}</span>
                </div>
                <div className="flex flex-col">
                  <span className="text-sm font-medium text-gray-500">Phone</span>
                  <span className="text-lg font-semibold text-gray-800">{admin.phone}</span>
                </div>
              </div>
              <div className="flex flex-col">
                <span className="text-sm font-medium text-gray-500">Status</span>
                <span
                  className={`text-lg font-semibold ${
                    admin.isActive ? 'text-green-500' : 'text-red-500'
                  }`}
                >
                  {admin.isActive ? 'Active' : 'Inactive'}
                </span>
              </div>
            </div>
          </div>
        )}
      </div>
    </div>
  );
};

export default Profile;