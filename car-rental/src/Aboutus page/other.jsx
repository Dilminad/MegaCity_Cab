import React from "react";
import { motion } from "framer-motion";

const Other = () => {
  return (
    <>
      <div>Other</div>
      <Stats />
    </>
  );
};

const Stats = () => {
  return (
    <section className="w-full bg-gray-900 text-white py-16">
      <div className="container mx-auto px-4">
        <div className="grid grid-cols-2 md:grid-cols-4 gap-8">
          {[
            {
              number: "5000+",
              label: "Happy Customers",
            },
            {
              number: "1000+",
              label: "Active Drivers",
            },
            {
              number: "25+",
              label: "Cities Covered",
            },
            {
              number: "24/7",
              label: "Support",
            },
          ].map((stat, index) => (
            <motion.div
              key={index}
              initial={{
                opacity: 0,
                scale: 0.5,
              }}
              whileInView={{
                opacity: 1,
                scale: 1,
              }}
              transition={{
                duration: 0.5,
                delay: index * 0.1,
              }}
              className="text-center"
            >
              <div className="text-3xl md:text-4xl font-bold text-blue-400 mb-2">
                {stat.number}
              </div>
              <div className="text-gray-400">{stat.label}</div>
            </motion.div>
          ))}
        </div>
      </div>
    </section>
  );
};

export default Other;
