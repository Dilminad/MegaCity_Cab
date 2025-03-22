import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Car, Image, Fuel, Settings, User, Lock } from "lucide-react"; // Fixed missing Lock icon import

const VehicleRegister = () => {
  const [formData, setFormData] = useState({
    vehicleImage: "",
    userName: "",
    password: "",
    vehicleNo: "",
    model: "",
    fuelType: "",
    transmission: "",
  });

  const [errors, setErrors] = useState({});
  const [isLoading, setIsLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleFileUpload = (e) => {
    const file = e.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onloadend = () => {
        setFormData({ ...formData, vehicleImage: reader.result });
      };
      reader.readAsDataURL(file);
    }
  };

  const validate = () => {
    let tempErrors = {};
    if (!formData.userName.trim()) tempErrors.userName = "Username is required";
    if (!formData.password.trim()) tempErrors.password = "Password is required";
    if (!formData.vehicleNo.trim()) tempErrors.vehicleNo = "Vehicle number is required";
    if (!formData.model.trim()) tempErrors.model = "Model is required";
    if (!formData.fuelType) tempErrors.fuelType = "Fuel type is required";
    if (!formData.transmission) tempErrors.transmission = "Transmission type is required";

    setErrors(tempErrors);
    return Object.keys(tempErrors).length === 0;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setErrorMessage("");

    if (!validate()) return;

    setIsLoading(true);

    try {
      const response = await fetch("http://localhost:8080/vehicle/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
      });

      if (!response.ok) {
        throw new Error(await response.text() || "Vehicle registration failed");
      }

      navigate("/dashboard");
    } catch (error) {
      setErrorMessage(error.message || "An error occurred during registration");
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <main className="min-h-screen w-full flex">
      {/* Left Side Image */}
      <div className="hidden lg:flex lg:w-1/2 relative">
        <img
          src="https://res.cloudinary.com/dzbpb4eis/image/upload/v1740406730/depositphotos_227882802-stock-photo-driver-opening-car-door-young_kdldb1.webp"
          alt="Vehicle registration"
          className="object-cover w-full h-full"
        />
      </div>

      {/* Registration Form */}
      <div className="w-full lg:w-1/2 bg-white flex items-center justify-center p-4">
        <div className="w-full max-w-md bg-white rounded-lg shadow-sm p-6 space-y-6">
          <h1 className="text-2xl font-semibold text-center">Vehicle Registration</h1>

          {errorMessage && <p className="text-red-500 text-sm text-center">{errorMessage}</p>}

          <form onSubmit={handleSubmit} className="space-y-4">
            {/* Vehicle Image */}
            <div className="space-y-2">
              <label className="block text-sm font-medium">Vehicle Image</label>
              <div className="relative">
                <Image className="absolute left-3 top-2 h-5 w-5 text-yellow-600" />
                <input type="file" name="vehicleImage" onChange={handleFileUpload} className="pl-10 w-full py-2 border rounded-md" />
              </div>
            </div>

            {/* Username */}
            <div className="space-y-2">
              <label className="block text-sm font-medium">Username</label>
              <div className="relative">
                <User className="absolute left-3 top-2 h-5 w-5 text-yellow-600" />
                <input type="text" name="userName" value={formData.userName} onChange={handleChange} className="pl-10 w-full py-2 border rounded-md" placeholder="Enter username" />
              </div>
              {errors.userName && <p className="text-red-500 text-xs">{errors.userName}</p>}
            </div>

            {/* Password */}
            <div className="space-y-2">
              <label className="block text-sm font-medium">Password</label>
              <div className="relative">
                <Lock className="absolute left-3 top-2 h-5 w-5 text-yellow-600" />
                <input type="password" name="password" value={formData.password} onChange={handleChange} className="pl-10 w-full py-2 border rounded-md" placeholder="Enter password" />
              </div>
              {errors.password && <p className="text-red-500 text-xs">{errors.password}</p>}
            </div>

            {/* Vehicle Number */}
            <div className="space-y-2">
              <label className="block text-sm font-medium">Vehicle Number</label>
              <div className="relative">
                <Car className="absolute left-3 top-2 h-5 w-5 text-yellow-600" />
                <input type="text" name="vehicleNo" value={formData.vehicleNo} onChange={handleChange} className="pl-10 w-full py-2 border rounded-md" placeholder="Enter vehicle number" />
              </div>
              {errors.vehicleNo && <p className="text-red-500 text-xs">{errors.vehicleNo}</p>}
            </div>

            {/* Model */}
            <div className="space-y-2">
              <label className="block text-sm font-medium">Model</label>
              <div className="relative">
                <Car className="absolute left-3 top-2 h-5 w-5 text-yellow-600" />
                <input type="text" name="model" value={formData.model} onChange={handleChange} className="pl-10 w-full py-2 border rounded-md" placeholder="Enter vehicle model" />
              </div>
              {errors.model && <p className="text-red-500 text-xs">{errors.model}</p>}
            </div>

            {/* Fuel Type */}
            <div className="space-y-2">
              <label className="block text-sm font-medium">Fuel Type</label>
              <div className="relative">
                <Fuel className="absolute left-3 top-2 h-5 w-5 text-yellow-600" />
                <select name="fuelType" value={formData.fuelType} onChange={handleChange} className="pl-10 w-full py-2 border rounded-md">
                  <option value="">Select fuel type</option>
                  <option value="Petrol">Petrol</option>
                  <option value="Diesel">Diesel</option>
                  <option value="Electric">Electric</option>
                </select>
              </div>
              {errors.fuelType && <p className="text-red-500 text-xs">{errors.fuelType}</p>}
            </div>

            {/* Transmission */}
            <div className="space-y-2">
              <label className="block text-sm font-medium">Transmission Type</label>
              <div className="relative">
                <Settings className="absolute left-3 top-2 h-5 w-5 text-yellow-600" />
                <input type="text" name="transmission" value={formData.transmission} onChange={handleChange} className="pl-10 w-full py-2 border rounded-md" placeholder="Enter transmission type" />
              </div>
              {errors.transmission && <p className="text-red-500 text-xs">{errors.transmission}</p>}
            </div>

            <button type="submit" className="w-full py-2 bg-yellow-600 text-white rounded-md">{isLoading ? "Registering..." : "Register"}</button>
          </form>
        </div>
      </div>
    </main>
  );
};

export default VehicleRegister;
