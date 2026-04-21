import React, { useState } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";
import "../styles/completeProfile.css";
import logo from "../assets/images/logo.png";

function CompleteProfile() {
  const { userId } = useParams();
  const navigate = useNavigate();
  const [fileName, setFileName] = useState("");

  const [profile, setProfile] = useState({
    firstName: "",
    lastName: "",
    location: "",
    education: "",
    experience: "",
    bio: "",
  });

  const handleFileChange = (e) => {
    if (e.target.files.length > 0) {
      setFileName(e.target.files[0].name);
    }
  };

  const handleChange = (e) => {
    setProfile({
      ...profile,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.put(
        `http://localhost:8082/api/users/${userId}/profile`,
        profile,
      );

      console.log("Saved:", response.data);

      alert("Profile completed successfully!");

      //redirect to dashboard
      navigate("/dashboard");
    } catch (error) {
      console.error("FULL ERROR:", error);
      console.error("RESPONSE:", error.response);

      alert(error.response?.data?.message || "Error saving profile");
    }
  };

  const handleLater = () => {
    console.log("Complete Later Clicked");
    navigate("/dashboard");
  };

  return (
    <div className="profile-container">
      <div className="profile-card">
        <img src={logo} className="logo" alt="CareerVia Logo" />
        <h2>Complete Your Profile</h2>
        <p>Just a few details to get started</p>

        <form onSubmit={handleSubmit} className="profile-form">
          <div className="row">
            <input
              type="text"
              name="firstName"
              placeholder="First Name"
              onChange={handleChange}
              required
            />

            <input
              type="text"
              name="lastName"
              placeholder="Last Name"
              onChange={handleChange}
              required
            />
          </div>

          <input
            type="text"
            name="location"
            placeholder="Location (City, Country)"
            onChange={handleChange}
          />

          <input
            type="text"
            name="education"
            placeholder="Education (e.g. B.Tech)"
            onChange={handleChange}
          />

          <label className="resume-upload">
            Add Resume
            <input
              type="file"
              accept=".pdf, .doc, .docx"
              hidden
              onChange={handleFileChange}
            ></input>
          </label>

          <select name="experience" onChange={handleChange}>
            <option value="">Select Experience</option>
            <option value="Fresher">Fresher</option>
            <option value="1-2 Years">1-2 years</option>
            <option value="3+ years">3+ years</option>
          </select>

          <textarea
            name="bio"
            placeholder="Tell us about yourself..."
            onChange={handleChange}
          />

          <button type="submit" className="save-btn">
            Save Profile
          </button>

          <button className="later-btn" onClick={handleLater}>
            Complete Later
          </button>
        </form>
      </div>
    </div>
  );
}

export default CompleteProfile;
