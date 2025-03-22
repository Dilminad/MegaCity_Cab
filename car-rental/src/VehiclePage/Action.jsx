import React from "react";
import { ArrowRightIcon } from "lucide-react";

const Action = () => {
  return (
    <section className="py-20 bg-white relative overflow-hidden">
      <div
        className="absolute inset-0 bg-cover bg-center opacity-10 z-0"
        style={{
          backgroundImage:
            "url('https://images.unsplash.com/photo-1503376780353-7e6692767b70?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1740&q=80')",
        }}
      ></div>
      <div className="container mx-auto px-4 relative z-10">
        <div className="max-w-3xl mx-auto text-center">
          <h2 className="text-3xl md:text-4xl font-bold mb-6">
            Ready to Register Your Vehicle?
          </h2>
          <p className="text-xl text-gray-600 mb-8">
            Complete your registration in minutes and enjoy the benefits of
            having your vehicle properly documented.
          </p>
          <button className="bg-black hover:bg-gray-800 transition-all text-white font-bold py-4 px-10 rounded-md text-lg flex items-center mx-auto space-x-2 group">
            <span>Register Now</span>
            <ArrowRightIcon className="w-5 h-5 transition-transform group-hover:translate-x-2" />
          </button>
        </div>
      </div>
    </section>
  );
};

export default Action;
