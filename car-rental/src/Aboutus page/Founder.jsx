import React from "react";
import { motion } from "framer-motion";

const Founder = () => {
  return (
    <section className="w-full bg-gray-50 py-16">
      <div className="container mx-auto px-4">
        <div className="max-w-6xl mx-auto">
          <div className="grid md:grid-cols-2 gap-12 items-center">
            <motion.div
              initial={{
                opacity: 0,
                x: -20,
              }}
              whileInView={{
                opacity: 1,
                x: 0,
              }}
              transition={{
                duration: 0.5,
              }}
            >
              <img
                src="https://images.unsplash.com/photo-1556157382-97eda2d62296?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=800&q=80"
                alt="Founder Rajitha Perera"
                className="rounded-lg shadow-xl w-full"
              />
            </motion.div>
            <motion.div
              initial={{
                opacity: 0,
                x: 20,
              }}
              whileInView={{
                opacity: 1,
                x: 0,
              }}
              transition={{
                duration: 0.5,
              }}
              className="space-y-6"
            >
              <h2 className="text-3xl font-bold text-gray-900">
                Meet Our Founder
              </h2>
              <h3 className="text-xl font-semibold text-blue-600">
                Rajitha Perera
              </h3>
              <p className="text-gray-600">
                With over 15 years of experience in the transportation industry,
                Rajitha founded our company with a vision to revolutionize Sri
                Lanka's mobility landscape. His passion for technology and
                commitment to community development has been the driving force
                behind our success.
              </p>
              <p className="text-gray-600">
                "Our mission goes beyond providing rides. We're building a
                platform that empowers drivers, ensures passenger safety, and
                contributes to Sri Lanka's economic growth. Every ride we
                provide is a step toward that vision."
              </p>
              <div className="pt-4">
                <p className="text-gray-900 font-semibold">Achievements:</p>
                <ul className="list-disc list-inside text-gray-600 mt-2 space-y-2">
                  <li>Young Entrepreneur of the Year 2020</li>
                  <li>Digital Innovation Award 2021</li>
                  <li>Transport Safety Excellence Award 2022</li>
                </ul>
              </div>
            </motion.div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default Founder;