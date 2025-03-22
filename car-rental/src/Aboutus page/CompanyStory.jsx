import React from "react";
import { motion } from "framer-motion";

const CompanyStory = () => {
  return (
    <section className="w-full bg-white py-16">
      <div className="container mx-auto px-4">
        <motion.div
          initial={{
            opacity: 0,
          }}
          whileInView={{
            opacity: 1,
          }}
          transition={{
            duration: 0.5,
          }}
          className="max-w-4xl mx-auto"
        >
          <h2 className="text-3xl font-bold text-gray-900 mb-8">Our Story</h2>
          <div className="space-y-6 text-gray-600">
            <p>
              Founded in 2018, our journey began with a simple yet powerful
              vision: to provide safe, reliable transportation to every corner
              of Sri Lanka while creating meaningful employment opportunities
              for our community. What started as a small fleet of just 10 cars
              in Colombo has now grown into a nationwide network of trusted
              drivers and satisfied customers.
            </p>
            <p>
              Our commitment to safety and reliability has never wavered. We've
              invested heavily in driver training, vehicle maintenance, and
              cutting-edge technology to ensure every ride meets our high
              standards. Today, we're proud to be one of Sri Lanka's leading
              transportation services, connecting people and places across the
              island.
            </p>
            <p>
              As we've grown, we've remained true to our core values of safety,
              reliability, and community empowerment. We believe in creating
              opportunities for our drivers, many of whom have been able to
              achieve financial independence and support their families through
              our platform.
            </p>
          </div>
        </motion.div>
      </div>
    </section>
  );
};

export default CompanyStory;
