import React from "react";
import { motion } from "framer-motion";
import { Shield, MapPin, Users } from "lucide-react";

const AboutHero = () => {
  return (
    <section className="w-full bg-gray-50 py-20">
      <div className="container mx-auto px-4">
        <motion.h1
          initial={{
            opacity: 0,
            y: 20,
          }}
          animate={{
            opacity: 1,
            y: 0,
          }}
          className="text-4xl md:text-5xl font-bold text-gray-900 text-center mb-6"
        >
          Our Journey to Transform Transportation in Sri Lanka
        </motion.h1>
        <motion.p
          initial={{
            opacity: 0,
            y: 20,
          }}
          animate={{
            opacity: 1,
            y: 0,
          }}
          transition={{
            delay: 0.2,
          }}
          className="text-xl text-gray-600 text-center max-w-3xl mx-auto mb-12"
        >
          Since 2018, we've been revolutionizing the way people travel across
          Sri Lanka, one safe ride at a time.
        </motion.p>
      </div>
    </section>
  );
};

export default AboutHero;
