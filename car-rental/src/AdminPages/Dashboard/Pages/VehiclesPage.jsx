import React from "react";
import { Search, Filter } from "lucide-react";

const vehicles = [
  {
    id: "1",
    model: "Toyota Camry",
    driver: "Mike Smith",
    status: "Active",
    licensePlate: "ABC-1234",
  },
  {
    id: "2",
    model: "Honda Civic",
    driver: "David Wilson",
    status: "Maintenance",
    licensePlate: "XYZ-5678",
  },
  {
    id: "3",
    model: "Ford Focus",
    driver: "Tom Davis",
    status: "Inactive",
    licensePlate: "LMN-4321",
  },
];

function VehiclesPage() {
  return (
    <div className="p-6 mt-16">
      <div className="mb-6 flex justify-between items-center">
        <h1 className="text-2xl font-semibold">Vehicles Management</h1>
        <div className="flex space-x-4">
          <div className="relative">
            <Search
              className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400"
              size={20}
            />
            <input
              type="text"
              placeholder="Search vehicles..."
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
              <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Model</th>
              <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Driver</th>
              <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">License Plate</th>
              <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Status</th>
              <th className="px-6 py-3 text-left text-sm font-medium text-gray-500">Actions</th>
            </tr>
          </thead>
          <tbody className="divide-y divide-gray-200">
            {vehicles.map((vehicle) => (
              <tr key={vehicle.id}>
                <td className="px-6 py-4 text-sm">#{vehicle.id}</td>
                <td className="px-6 py-4 text-sm">{vehicle.model}</td>
                <td className="px-6 py-4 text-sm">{vehicle.driver}</td>
                <td className="px-6 py-4 text-sm">{vehicle.licensePlate}</td>
                <td className="px-6 py-4 text-sm">
                  <span
                    className={`px-3 py-1 rounded-full text-xs ${
                      vehicle.status === "Active"
                        ? "bg-green-100 text-green-600"
                        : vehicle.status === "Maintenance"
                        ? "bg-yellow-100 text-yellow-600"
                        : "bg-gray-100 text-gray-600"
                    }`}
                  >
                    {vehicle.status}
                  </span>
                </td>
                <td className="px-6 py-4 text-sm">
                  <button className="text-blue-600 hover:text-blue-800">View Details</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        <button className="block w-full py-3 text-center bg-blue-600 text-white font-semibold hover:bg-blue-700">
          Pending Vehicles
        </button>
      </div>
    </div>
  );
}

export default VehiclesPage;
