import React, { useEffect, useState } from "react";
import {
  ShieldIcon,
  ClockIcon,
  BellIcon,
  ZapIcon,
} from "lucide-react";

export const Benefits = () => {
  const [animate, setAnimate] = useState(false);

  useEffect(() => {
    const handleScroll = () => {
      const element = document.getElementById("benefits-section");
      if (element) {
        const position = element.getBoundingClientRect();
        if (position.top < window.innerHeight * 0.75) {
          setAnimate(true);
        }
      }
    };

    window.addEventListener("scroll", handleScroll);
    handleScroll(); // Check on initial load
    return () => {
      window.removeEventListener("scroll", handleScroll);
    };
  }, []);

  const benefits = [
    {
      icon: <ShieldIcon className="w-10 h-10 text-yellow-500" />, 
      title: "Enhanced Security",
      description: "Advanced tracking and security features to protect your vehicle",
    },
    {
      icon: <ClockIcon className="w-10 h-10 text-yellow-500" />, 
      title: "24/7 Access",
      description: "Access your vehicle information anytime, anywhere",
    },
    {
      icon: <BellIcon className="w-10 h-10 text-yellow-500" />, 
      title: "Instant Notifications",
      description: "Get real-time alerts about your vehicle status",
    },
    {
      icon: <ZapIcon className="w-10 h-10 text-yellow-500" />, 
      title: "Quick Processing",
      description: "Fast and efficient registration process",
    },
  ];

  return (
    <section id="benefits-section" className="py-20 bg-gray-50">
      <div className="container mx-auto px-4">
        <div className="text-center mb-16">
          <h2 className="text-3xl md:text-4xl font-bold mb-4">
            Benefits of Registration
          </h2>
          <p className="text-gray-600 max-w-2xl mx-auto">
            Registering your vehicle provides numerous advantages that protect
            both you and your investment.
          </p>
        </div>
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
          {benefits.map((benefit, index) => (
            <div
              key={index}
              className={`bg-white p-6 rounded-lg shadow-md border-t-4 border-yellow-500 transition-all duration-700 transform ${animate ? "opacity-100 translate-y-0" : "opacity-0 translate-y-10"}`}
              style={{
                transitionDelay: `${index * 150}ms`,
              }}
            >
              <div className="mb-4">{benefit.icon}</div>
              <h3 className="text-xl font-bold mb-2">{benefit.title}</h3>
              <p className="text-gray-600">{benefit.description}</p>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
};

export default Benefits;
