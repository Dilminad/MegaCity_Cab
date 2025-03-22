import React, { useEffect, useState } from "react";
import { ChevronRightIcon } from "lucide-react";
import { Link } from "react-router-dom"; // Using react-router-dom

const Hero = () => {
  const [isVisible, setIsVisible] = useState(false);

  useEffect(() => {
    setIsVisible(true);
  }, []);

  return (
    <div className="relative w-full bg-black text-white overflow-hidden">
      <div
        className="absolute inset-0 bg-cover bg-center z-0 opacity-50"
        style={{
          backgroundImage:
            "url('https://images.unsplash.com/photo-1492144534655-ae79c964c9d7?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1920&q=80')",
        }}
      ></div>
      <div className="relative z-10 container mx-auto px-4 py-24 md:py-32 lg:py-40">
        <div
          className="max-w-3xl transition-all duration-1000 transform"
          style={{
            opacity: isVisible ? 1 : 0,
            transform: isVisible ? "translateY(0)" : "translateY(20px)",
          }}
        >
          <h1 className="text-4xl md:text-5xl lg:text-6xl font-bold mb-6">
            Register Your Vehicle With Confidence
          </h1>
          <p className="text-xl md:text-2xl text-gray-300 mb-8">
            Streamline your vehicle documentation process with our secure,
            efficient registration platform.
          </p>
          <Link to="/vehicle-register">
            <button className="bg-yellow-500 hover:bg-yellow-400 transition-colors text-black font-bold py-3 px-8 rounded-md flex items-center space-x-2 group">
              <span>Register Now</span>
              <ChevronRightIcon className="w-5 h-5 transition-transform group-hover:translate-x-1" />
            </button>
          </Link>
        </div>
      </div>
      <div className="absolute bottom-0 left-0 right-0 h-16 bg-gradient-to-t from-black to-transparent"></div>
    </div>
  );
};

export default Hero;
