import React from 'react'
import HeroImage from '../assets/Hero-1.jpeg'; 

const Hero = () => {
  return (
    <section
    className="relative bg-cover bg-center h-screen"
    style={{ backgroundImage: `url(${HeroImage})` }}
  >
    {/* Dark overlay for contrast */}
    <div className="absolute inset-0 bg-black opacity-50"></div>
    <div className="container grid grid-cols-1 md:grid-cols-2 min-h-[650px] relative z-10 px-4 py-14 md:py-0">
      {/* Text and Call to Action */}
      <div className="flex flex-col justify-center text-white space-y-6 font-playfair text-center md:text-left">
        <h1 className="text-5xl lg:text-6xl font-bold leading-tight xl:leading-normal">
          We Give You Safe <span className="text-yellow-400">Rides</span>
        </h1>
        <p className="text-lg md:text-xl max-w-3xl mx-auto md:mx-0">
          We offer a reliable, secure, and comfortable ride experience every time you travel with us.
          Our fleet of well-maintained vehicles is equipped with modern amenities to ensure your comfort,
          while our experienced and professional drivers prioritize your safety.
        </p>
        {/* Call to Action Button */}
        <div className='flex justify-center items-center gap-8 md:justify-start !mt-4'>
          <button className="bg-yellow-400 text-white py-3 px-8 rounded-full font-semibold text-lg shadow-lg hover:bg-yellow-500 hover:shadow-xl transition-all duration-300">
            Book a Ride
          </button>
        </div>
      </div>

     
    </div>
  </section>
);
};  


export default Hero