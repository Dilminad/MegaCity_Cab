import React from 'react'
import AboutHero from './AboutHero'
import CompanyStory from './CompanyStory'
import Founder from './Founder'
import Other from './other'
import Navbar from '../HomePage/Navbar'
import Footer from '../HomePage/footer'

const AboutUsPage = () => {
  return (
    <div>
        <Navbar />
        <AboutHero />
        <CompanyStory   />
        <Founder />
        <Other />
        <Footer />
    </div>
  )
}

export default AboutUsPage