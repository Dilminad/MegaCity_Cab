import React from "react";
import { Users, Car, DollarSign, BoxIcon } from "lucide-react";

const stats = [
  {
    icon: BoxIcon,
    label: "Active Rides",
    value: "24",
    change: "+12%",
  },
  {
    icon: Users,
    label: "Total Drivers",
    value: "156",
    change: "+5%",
  },
  {
    icon: Car,
    label: "Registered Vehicles",
    value: "89",
    change: "+3%",
  },
  {
    icon: DollarSign,
    label: "Today's Revenue",
    value: "$2,415",
    change: "+8%",
  },
];

const DashboardStats = () => {
  return (
    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-6">
      {stats.map((stat, index) => (
        <div key={index} className="bg-white rounded-xl p-6 shadow-sm">
          <div className="flex items-center justify-between mb-4">
            <div className="bg-blue-50 p-3 rounded-lg">
              <stat.icon className="text-blue-500" size={24} />
            </div>
            <span className="text-green-500 text-sm font-medium">
              {stat.change}
            </span>
          </div>
          <h3 className="text-gray-500 text-sm mb-1">{stat.label}</h3>
          <p className="text-2xl font-semibold">{stat.value}</p>
        </div>
      ))}
    </div>
  );
};

export default DashboardStats;
