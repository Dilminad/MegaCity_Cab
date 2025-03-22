import { useState } from "react";
import "./App.css";
import { Routes, Route } from "react-router-dom";
import Header from "./HomePage/Header";
import Hero from "./HomePage/Hero";
import Login from "./LoginPage/Login";
import Navbar from "./HomePage/Navbar";
import Homepage from "./HomePage/Homepage";
import Footer from "./HomePage/footer";
import Register from "./LoginPage/Register";
import DriverPage from "./DriverPage/DriverPage";
import AboutUsPage from "./Aboutus page/AboutUsPage";
import AdminApp from "./AdminPages/AdminApp";
import VehicleApp from "./VehiclePage/VehicleApp";
import VehicleRegister from "./VehiclePage/VehicleRegister/VehicleRegister";


function App() {
  const [count, setCount] = useState(0);

  return (
    <>
      <Routes>
        <Route
          path="/"
          element={
            <main>
              <Navbar />
              <Header />
              <Hero />
              <Homepage />
              <Footer />
            </main>
          }
        />
        <Route path="login" element={<Login />} />
        <Route path="register" element={<Register />} />
        <Route path="driver" element={<DriverPage />} />
        <Route path="vehicle" element={<VehicleApp/>} />
        <Route path="about" element={<AboutUsPage />} />

        
        <Route path="/vehicle-register" element={<VehicleRegister/>} />
        

        {/* ✅ Corrected Admin Route */}
        <Route path="/admin/*" element={<AdminApp />} />


       
      </Routes>
    </>
  );
}

export default App;
