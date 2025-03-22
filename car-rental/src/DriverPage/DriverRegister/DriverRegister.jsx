import React, { useState } from "react";
import { Mail, Lock, User, Phone, IdCard, Car, MapPin, Calendar, AlertCircle } from "lucide-react";
import { useNavigate } from "react-router-dom"; // For navigation
import axios from "axios";

const DriverRegister = () => {
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    email: "",
    nic: "",
    phone: "",
    emergencyPhone: "",
    address: "",
    licenseNumber: "",
    licenseExpiry: "",
    experienceYears: "",
    username: "",
    password: "",
    licenseImage: null, // For file upload
  });

  const [errors, setErrors] = useState({});
  const [isLoading, setIsLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate(); // Initialize useNavigate

  // Handle input change
  const handleChange = (e) => {
    const { name, value, files } = e.target;
    if (name === "licenseImage") {
      setFormData({ ...formData, [name]: files[0] }); // Handle file upload
    } else {
      setFormData({ ...formData, [name]: value });
    }
  };

  // Validate form fields
  const validate = () => {
    let tempErrors = {};
    if (!formData.firstName.trim()) tempErrors.firstName = "First name is required";
    if (!formData.lastName.trim()) tempErrors.lastName = "Last name is required";
    
    if (!formData.email.trim()) tempErrors.email = "Email is required";
    else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(formData.email))
      tempErrors.email = "Invalid email address";

    if (!formData.nic.trim()) tempErrors.nic = "NIC is required";

    if (!formData.phone.trim()) tempErrors.phone = "Phone number is required";
    else if (!/^\d{10}$/.test(formData.phone))
      tempErrors.phone = "Phone number must be 10 digits";

    if (formData.emergencyPhone && !/^\d{10}$/.test(formData.emergencyPhone))
      tempErrors.emergencyPhone = "Emergency phone must be 10 digits";

    if (!formData.licenseNumber.trim()) tempErrors.licenseNumber = "License number is required";
    if (!formData.licenseExpiry) tempErrors.licenseExpiry = "License expiry date is required";
    if (!formData.licenseImage) tempErrors.licenseImage = "License image is required";

    if (!formData.experienceYears) tempErrors.experienceYears = "Experience years is required";
    else if (isNaN(formData.experienceYears) || formData.experienceYears < 0)
      tempErrors.experienceYears = "Experience years must be a valid number";

    if (!formData.username.trim()) tempErrors.username = "Username is required";

    if (!formData.password.trim()) tempErrors.password = "Password is required";
    else if (formData.password.length < 8)
      tempErrors.password = "Password must be at least 8 characters";

    setErrors(tempErrors);
    return Object.keys(tempErrors).length === 0;
  };

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();
    setErrorMessage("");

    if (!validate()) return;

    setIsLoading(true);

    try {
      const formDataToSend = new FormData();
      for (const key in formData) {
        formDataToSend.append(key, formData[key]);
      }

      const response = await axios.post("http://localhost:8080/auth/driverregister", formDataToSend, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });

      if (response.status === 201) {
        console.log("Driver registered successfully:", response.data);
        navigate("/driver-login"); // Redirect to login page after successful registration
      }
    } catch (error) {
      console.error("Registration Error:", error.response?.data || error.message);
      setErrorMessage(error.response?.data?.message || "An error occurred during registration");
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <main className="min-h-screen w-full flex">
      {/* Left Side Image */}
      <div className="hidden lg:flex lg:w-1/2 relative">
        <div className="absolute inset-0 bg-gradient-to-t from-black/40 to-transparent z-10" />
        <img
          src="https://res.cloudinary.com/dzbpb4eis/image/upload/v1740406730/depositphotos_227882802-stock-photo-driver-opening-car-door-young_kdldb1.webp"
          alt="Driver opening car door"
          className="object-cover w-full h-full"
        />
        <div className="absolute bottom-0 left-0 right-0 p-8 z-20">
          <h2 className="text-white text-3xl font-bold">Join Mega City Cab</h2>
          <p className="text-white/90 mt-2">Register as a driver and start your journey with us.</p>
        </div>
      </div>

      {/* Registration Form */}
      <div className="w-full lg:w-1/2 bg-white flex items-center justify-center p-4">
        <div className="w-full max-w-md">
          <div className="bg-white rounded-lg shadow-sm p-6 space-y-6">
            <div className="text-center">
              <h1 className="text-2xl font-semibold text-gray-900">Driver Registration</h1>
              <p className="text-gray-600 mt-2">Sign up to join our team of professional drivers.</p>
            </div>

            {errorMessage && (
              <p className="text-red-500 text-sm text-center">{errorMessage}</p>
            )}

            <form onSubmit={handleSubmit} className="space-y-4">
              {/* Full Name */}
              <div className="grid grid-cols-2 gap-4">
                <input
                  type="text"
                  name="firstName"
                  placeholder="First Name"
                  value={formData.firstName}
                  onChange={handleChange}
                  className="block w-full px-3 py-2 border rounded-md"
                />
                <input
                  type="text"
                  name="lastName"
                  placeholder="Last Name"
                  value={formData.lastName}
                  onChange={handleChange}
                  className="block w-full px-3 py-2 border rounded-md"
                />
              </div>
              {errors.firstName && <p className="text-red-500 text-xs">{errors.firstName}</p>}
              {errors.lastName && <p className="text-red-500 text-xs">{errors.lastName}</p>}

              {/* Email */}
              <div className="relative">
                <Mail className="absolute left-3 top-3 h-5 w-5 text-yellow-600" />
                <input
                  type="email"
                  name="email"
                  placeholder="Email"
                  value={formData.email}
                  onChange={handleChange}
                  className="block w-full pl-10 px-3 py-2 border rounded-md"
                />
              </div>
              {errors.email && <p className="text-red-500 text-xs">{errors.email}</p>}

              {/* NIC */}
              <div className="relative">
                <IdCard className="absolute left-3 top-3 h-5 w-5 text-yellow-600" />
                <input
                  type="text"
                  name="nic"
                  placeholder="NIC"
                  value={formData.nic}
                  onChange={handleChange}
                  className="block w-full pl-10 px-3 py-2 border rounded-md"
                />
              </div>
              {errors.nic && <p className="text-red-500 text-xs">{errors.nic}</p>}

              {/* Phone */}
              <div className="relative">
                <Phone className="absolute left-3 top-3 h-5 w-5 text-yellow-600" />
                <input
                  type="tel"
                  name="phone"
                  placeholder="Phone Number"
                  value={formData.phone}
                  onChange={handleChange}
                  className="block w-full pl-10 px-3 py-2 border rounded-md"
                />
              </div>
              {errors.phone && <p className="text-red-500 text-xs">{errors.phone}</p>}

              {/* License Number */}
              <div className="relative">
                <IdCard className="absolute left-3 top-3 h-5 w-5 text-yellow-600" />
                <input
                  type="text"
                  name="licenseNumber"
                  placeholder="License Number"
                  value={formData.licenseNumber}
                  onChange={handleChange}
                  className="block w-full pl-10 px-3 py-2 border rounded-md"
                />
              </div>
              {errors.licenseNumber && <p className="text-red-500 text-xs">{errors.licenseNumber}</p>}

              {/* License Expiry */}
              <div className="relative">
                <Calendar className="absolute left-3 top-3 h-5 w-5 text-yellow-600" />
                <input
                  type="date"
                  name="licenseExpiry"
                  placeholder="License Expiry Date"
                  value={formData.licenseExpiry}
                  onChange={handleChange}
                  className="block w-full pl-10 px-3 py-2 border rounded-md"
                />
              </div>
              {errors.licenseExpiry && <p className="text-red-500 text-xs">{errors.licenseExpiry}</p>}

              {/* Experience Years */}
              <div className="relative">
                <input
                  type="number"
                  name="experienceYears"
                  placeholder="Experience Years"
                  value={formData.experienceYears}
                  onChange={handleChange}
                  className="block w-full px-3 py-2 border rounded-md"
                />
              </div>
              {errors.experienceYears && <p className="text-red-500 text-xs">{errors.experienceYears}</p>}

              {/* Username */}
              <div className="relative">
                <User className="absolute left-3 top-3 h-5 w-5 text-yellow-600" />
                <input
                  type="text"
                  name="username"
                  placeholder="Username"
                  value={formData.username}
                  onChange={handleChange}
                  className="block w-full pl-10 px-3 py-2 border rounded-md"
                />
              </div>
              {errors.username && <p className="text-red-500 text-xs">{errors.username}</p>}

              {/* Password */}
              <div className="relative">
                <Lock className="absolute left-3 top-3 h-5 w-5 text-yellow-600" />
                <input
                  type="password"
                  name="password"
                  placeholder="Password"
                  value={formData.password}
                  onChange={handleChange}
                  className="block w-full pl-10 px-3 py-2 border rounded-md"
                />
              </div>
              {errors.password && <p className="text-red-500 text-xs">{errors.password}</p>}

              {/* License Image */}
              <div className="relative">
                <input
                  type="file"
                  name="licenseImage"
                  onChange={handleChange}
                  className="block w-full px-3 py-2 border rounded-md"
                  accept="image/*"
                />
              </div>
              {errors.licenseImage && <p className="text-red-500 text-xs">{errors.licenseImage}</p>}

              {/* Submit */}
              <button
                type="submit"
                className="w-full bg-yellow-500 text-white py-2 rounded-md hover:bg-yellow-600 transition"
                disabled={isLoading}
              >
                {isLoading ? "Registering..." : "Register"}
              </button>
            </form>
          </div>
        </div>
      </div>
    </main>
  );
};

export default DriverRegister;