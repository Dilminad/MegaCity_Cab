import React from "react";
import { Search, Filter } from "lucide-react";

const settings = [
  {
    id: "1",
    setting: "Notification Preferences",
    status: "Active",
  },
  {
    id: "2",
    setting: "Account Privacy",
    status: "Inactive",
  },
  {
    id: "3",
    setting: "Language Preferences",
    status: "Active",
  },
];

const SettingsPage = () => {
  return (
    <div className="p-6 mt-16">
      <div className="mb-6 flex justify-between items-center">
        <h1 className="text-2xl font-semibold">Settings Management</h1>
        <div className="flex space-x-4">
          <div className="relative">
            <Search
              className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400"
              size={20}
            />
            <input
              type="text"
              placeholder="Search settings..."
              className="pl-10 pr-4 py-2 border rounded-lg w-64"
            />
          </div>
          <button className="flex items-center space-x-2 px-4 py-2 border rounded-lg hover:bg-gray-50">
            <Filter size={20} />
            <span>Filter</span>
          </button>
        </div>
      </div>
      <div className="bg-white rounded-xl shadow-sm overflow-hidden">
        <table className="w-full">
          <thead className="bg-gray-50">
            <tr>
              <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">ID</th>
              <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Setting</th>
              <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Status</th>
              <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Actions</th>
            </tr>
          </thead>
          <tbody className="divide-y divide-gray-200">
            {settings.map((setting) => (
              <tr key={setting.id}>
                <td className="px-6 py-4 text-sm">#{setting.id}</td>
                <td className="px-6 py-4 text-sm">{setting.setting}</td>
                <td className="px-6 py-4 text-sm">
                  <span
                    className={`px-3 py-1 rounded-full text-xs ${setting.status === "Active" ? "bg-green-100 text-green-600" : "bg-gray-100 text-gray-600"}`}
                  >
                    {setting.status}
                  </span>
                </td>
                <td className="px-6 py-4 text-sm">
                  <button className="text-blue-600 hover:text-blue-800">
                    View Details
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default SettingsPage;
