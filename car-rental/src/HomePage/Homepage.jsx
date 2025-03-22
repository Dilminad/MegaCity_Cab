import React from "react";
import { Car, Plane, Crown, Heart, Bike, Users } from "lucide-react";
import { motion } from "framer-motion";

const services = [
  {
    icon: <Car className="w-12 h-12" />, 
    title: "City Rides",
    description: "Enjoy smooth and secure rides around the city with our premium service",
    image: "https://res.cloudinary.com/dzbpb4eis/image/upload/v1740374528/converted_1_rhddls.jpg",
  },
  {
    icon: <Plane className="w-12 h-12" />,
    title: "Airport Transfers",
    description: "Reliable pick-up and drop-off service for all major airports",
    image: "https://res.cloudinary.com/dzbpb4eis/image/upload/v1740374857/0_-QKajWHoVTqYt7VT_wco8mu.jpg",
  },
  {
    icon: <Crown className="w-12 h-12" />,
    title: "Luxury Rides",
    description: "Experience top-tier comfort and style in our luxury fleet",
    image: "https://res.cloudinary.com/dzbpb4eis/image/upload/v1740374548/vip_lygcnu.jpg",
  },
  {
    icon: <Heart className="w-12 h-12" />,
    title: "Wedding Rides",
    description: "Make your special day unforgettable with our elegant wedding rides",
    image: "https://res.cloudinary.com/dzbpb4eis/image/upload/v1740375276/wedding_car_rental_1731499085_e87635a5_oubh8t.jpg",
  },
  {
    icon: <Bike className="w-12 h-12" />,
    title: "Tuk Tuk Rides",
    description: "Enjoy fun and convenient tuk tuk rides across the city",
    image: "https://res.cloudinary.com/dzbpb4eis/image/upload/v1740374539/adventure-the-sandy-roads_dwv60j.jpg",
  },
  {
    icon: <Users className="w-12 h-12" />,
    title: "Own Rides",
    description: "Ride anywhere with your family and friends",
    image: "https://res.cloudinary.com/dzbpb4eis/image/upload/v1740374783/iStock-1137373614-scaled_pbrwgh.jpg",
  },
];

const Homepage = () => {
  return (
    <div>
      {/* Services Section */}
      <section className="py-24 bg-gradient-to-b from-white to-gray-50">
        <div className="max-w-7xl mx-auto px-4">
          <motion.div
            initial={{ opacity: 0, y: 20 }}
            whileInView={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.6 }}
            className="text-center mb-16"
          >
            <h2 className="text-4xl font-bold mb-6">Our Premium Services</h2>
            <p className="text-lg text-gray-600 max-w-2xl mx-auto">
              We provide safe, reliable, and comfortable rides with experienced drivers and well-maintained vehicles.
            </p>
          </motion.div>
          <motion.div
            className="grid grid-cols-1 md:grid-cols-3 gap-8"
            initial="hidden"
            whileInView="visible"
            viewport={{ once: true }}
          >
            {services.map((service, index) => (
              <motion.div
                key={index}
                initial={{ y: 20, opacity: 0 }}
                animate={{ y: 0, opacity: 1 }}
                transition={{ duration: 0.5, delay: index * 0.2 }}
                className="group relative overflow-hidden rounded-xl bg-white shadow-lg hover:shadow-xl transition-all duration-300"
              >
                <div className="relative h-48 overflow-hidden">
                  <div className="absolute inset-0 bg-black/40 z-10" />
                  <img
                    src={service.image}
                    alt={service.title}
                    className="w-full h-full object-cover transform group-hover:scale-110 transition-transform duration-300"
                  />
                  <div className="absolute top-4 left-4 z-20 text-white">
                    {service.icon}
                  </div>
                </div>
                <div className="p-6">
                  <h3 className="text-2xl font-semibold mb-3">{service.title}</h3>
                  <p className="text-gray-600">{service.description}</p>
                  <motion.button
                    whileHover={{ scale: 1.05 }}
                    whileTap={{ scale: 0.95 }}
                    className="mt-4 px-6 py-2 bg-black text-white rounded-full text-sm font-medium hover:bg-gray-800 transition-colors"
                  >
                    Learn More
                  </motion.button>
                </div>
              </motion.div>
            ))}
          </motion.div>
        </div>
      </section>
    </div>
  );
};

export default Homepage;