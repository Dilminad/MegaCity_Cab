import React from "react";
import { Phone, Mail, MapPin } from "lucide-react";

const Footer = () => {
  return (
    <footer id="contact" className="w-full bg-gray-900 text-white py-10 mt-auto">
      {/* Main Content */}
      <div className="max-w-7xl mx-auto px-6 lg:px-8 grid grid-cols-1 md:grid-cols-3 gap-8">
        {/* Company Info */}
        <div>
          <h3 className="text-2xl font-bold text-yellow-500 mb-4">Mega City Cab</h3>
          <p className="text-gray-400">
            Experience luxury transportation at its finest. Available 24/7 for your comfort.
          </p>
        </div>

        {/* Quick Links */}
        <div>
          <h4 className="text-lg font-semibold mb-4">Quick Links</h4>
          <ul className="space-y-2">
            {["Home", "Rides", "Drive", "Vehicle", "Booking", "Contact"].map((item) => (
              <li key={item}>
                <a href={`/${item.toLowerCase()}`} className="hover:text-yellow-500 transition-colors">
                  {item}
                </a>
              </li>
            ))}
          </ul>
        </div>

        {/* Contact Info */}
        <div>
          <h4 className="text-lg font-semibold mb-4">Contact Us</h4>
          <div className="space-y-2">
            <p className="flex items-center gap-2">
              <Phone size={20} className="text-yellow-500" />
              <span>+1 (555) 123-4567</span>
            </p>
            <p className="flex items-center gap-2">
              <Mail size={20} className="text-yellow-500" />
              <span>contact@megacitycab.com</span>
            </p>
            <p className="flex items-center gap-2">
              <MapPin size={20} className="text-yellow-500" />
              <span>123 Luxury Drive, City, State</span>
            </p>
          </div>
        </div>
      </div>

      {/* Full-Width Border with Debugging */}
      <div className="w-full border-t border-gray-700 mt-6">
        <div className="max-w-7xl mx-auto px-6 lg:px-8 py-4 text-center">
          <p className="text-gray-400">
            © {new Date().getFullYear()} Mega City Cab. All rights reserved.
          </p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;