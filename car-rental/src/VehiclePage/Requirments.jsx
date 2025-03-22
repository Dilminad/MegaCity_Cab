import React from "react";
import {
  FileTextIcon,
  CarIcon,
  BadgeCheckIcon,
  ShieldIcon,
} from "lucide-react";

const Requirements = () => {
  const requirements = [
    {
      icon: <CarIcon className="w-8 h-8 text-yellow-500" />,
      title: "Vehicle Requirements",
      description:
        "4-door vehicle, less than 7 years old, good condition with valid inspection.",
    },
    {
      icon: <BadgeCheckIcon className="w-8 h-8 text-yellow-500" />,
      title: "Driver Requirements",
      description:
        "Valid taxi license, minimum 3 years driving experience, clean record.",
    },
    {
      icon: <ShieldIcon className="w-8 h-8 text-yellow-500" />,
      title: "Documentation",
      description:
        "Commercial insurance, vehicle registration, taxi permit documentation.",
    },
    {
      icon: <FileTextIcon className="w-8 h-8 text-yellow-500" />,
      title: "Background Check",
      description:
        "Must pass background check and have a clean driving record.",
    },
  ];

  return (
    <section className="py-20 bg-black text-white">
      <div className="container mx-auto px-4">
        <div className="text-center mb-16">
          <h2 className="text-3xl md:text-4xl font-bold mb-4">
            Registration Requirements
          </h2>
          <p className="text-gray-400 max-w-2xl mx-auto">
            Ensure you meet these requirements before registering your taxi in
            our network.
          </p>
        </div>
        <div className="grid grid-cols-1 md:grid-cols-2 gap-8 max-w-4xl mx-auto">
          {requirements.map((req, index) => (
            <div
              key={index}
              className="flex items-start p-6 bg-gray-900 rounded-lg hover:bg-gray-800 transition-all duration-300"
            >
              <div className="mr-4 mt-1">{req.icon}</div>
              <div>
                <h3 className="text-xl font-bold mb-2">{req.title}</h3>
                <p className="text-gray-400">{req.description}</p>
              </div>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
};

export default Requirements;
