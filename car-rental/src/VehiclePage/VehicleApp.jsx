import React from 'react'
import Hero from './Hero'
import Benefits from './Benefits'
import Requirements from './Requirments'
import Action from './Action'
import Navbar from '../HomePage/Navbar'

const VehicleApp = () => {
  return (
    <div className='w-full min-h-screen bg-white'>
      <Navbar/>
      <Hero />
      <Benefits />
      <Requirements />
      <Action />
      

    </div>
  )
}

export default VehicleApp