import React, { useState } from "react";
import "../styles/RecruiterRegister.css";
import { Link } from "react-router-dom";
import logo from "../assets/images/logo.png";

const RecruiterRegister = () => {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    phone: "",
    password: "",
    companyName: "",
    website: "",
    location: "",
    industry: "Technology",
    designation: "",
    description: "",
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const dataToSend = {
      ...formData,
      status: "ACTIVE",
    };

    try {
      const response = await fetch("http://localhost:8081/api/hrs/add", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(dataToSend),
      });

      if (!response.ok) {
        const errorText = await response.text();
        throw new Error(errorText);
      }

      const result = await response.json();

      console.log("Saved:", result);
      alert("Registration Successful ");

      setFormData({
        name: "",
        email: "",
        phone: "",
        password: "",
        companyName: "",
        website: "",
        location: "",
        industry: "Technology",
        designation: "",
        description: "",
      });
    } catch (error) {
      console.error("Error:", error);
      alert("Error: Failed to fetch ❌");
    }
  };

  return (
    <div className="recruiter-container">
      {/* ✅ UPDATED HEADER WITH LOGO */}
      <div className="recruiter-title">
        <img src={logo} alt="Company Logo" className="logo" />

        <h1>
          Career<span>Via</span>
        </h1>
        <h2>Recruiter Registration</h2>
        <p className="recruiter-subtitle">
          Create your corporate account to start hiring
        </p>
      </div>

      <form onSubmit={handleSubmit}>
        <div className="recruiter-form-grid">
          {/* Personal Info */}
          <div>
            <div className="recruiter-section-title">Personal Information</div>

            <label>Full Name</label>
            <input
              type="text"
              name="name"
              value={formData.name}
              onChange={handleChange}
            />

            <label>Email Address</label>
            <input
              type="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
            />

            <label>Contact Number</label>
            <input
              type="text"
              name="phone"
              value={formData.phone}
              onChange={handleChange}
            />

            <div className="recruiter-two-col">
              <div>
                <label>Password</label>
                <input
                  type="password"
                  name="password"
                  value={formData.password}
                  onChange={handleChange}
                />
              </div>

              <div>
                <label>Confirm Password</label>
                <input type="password" />
              </div>
            </div>
          </div>

          {/* Company Info */}
          <div>
            <div className="recruiter-section-title">Company Information</div>

            <label>Company Name</label>
            <input
              type="text"
              name="companyName"
              value={formData.companyName}
              onChange={handleChange}
            />

            <div className="recruiter-two-col">
              <div>
                <label>Website</label>
                <input
                  type="text"
                  name="website"
                  value={formData.website}
                  onChange={handleChange}
                />
              </div>

              <div>
                <label>Location</label>
                <input
                  type="text"
                  name="location"
                  value={formData.location}
                  onChange={handleChange}
                />
              </div>
            </div>

            <div className="recruiter-two-col">
              <div>
                <label>Industry Type</label>
                <select
                  name="industry"
                  value={formData.industry}
                  onChange={handleChange}
                >
                  <option>Technology</option>
                  <option>Finance</option>
                  <option>Healthcare</option>
                  <option>Education</option>
                </select>
              </div>

              <div>
                <label>Designation</label>
                <input
                  type="text"
                  name="designation"
                  value={formData.designation}
                  onChange={handleChange}
                />
              </div>
            </div>

            <label>Company Description</label>
            <textarea
              name="description"
              value={formData.description}
              onChange={handleChange}
            ></textarea>
          </div>
        </div>

        <div className="recruiter-footer">
          <button type="submit">Register</button>
        </div>
        <div className="recruiter-text">
          <p>
            Already have an account? <Link to="/login">Sign In</Link>
          </p>
        </div>
      </form>
    </div>
  );
};

export default RecruiterRegister;
