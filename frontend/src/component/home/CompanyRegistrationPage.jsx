import React, { useState } from 'react';
import styles from "./CompanyRegisterationPage.module.css";
import logo from "../../assets/images/logo.png";
import { Link } from 'react-router-dom';

function CompanyRegistrationPage() {
  const [formData, setFormData] = useState({
    companyName: "",
    email: "",
    password: "",
    industry: "",
    location: "",
    website: "",
    description: "",
    status: "active"
  }); 

  const [errors, setErrors] = useState({});
  const [successMsg, setSuccessMsg] = useState("");

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const validate = () => {
    let tempErrors = {};

    if (!formData.companyName) tempErrors.companyName = "Company Name is required";

    if (!formData.email) {
      tempErrors.email = "Email is required";
    }

    if (!formData.password) {
      tempErrors.password = "Password is required";
    } else if (formData.password.length < 8) {
      tempErrors.password = "Password must be at least 8 characters";
    }

    if (!formData.industry) tempErrors.industry = "Industry is required";

    if (!formData.location) tempErrors.location = "Location is required";

    if (!formData.website) tempErrors.website = "Website is required";

    if (!formData.description) tempErrors.description = "Description is required";
    
    if (!formData.status) tempErrors.status = "Status is required";

    setErrors(tempErrors);
    return Object.keys(tempErrors).length === 0;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!validate()) {
      return;
    }

    try{
      const response = await fetch("http://localhost:8081/api/companies", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(formData)
      });

      const text = await response.text();
    const data = text ? JSON.parse(text) : {};

      if (response.ok) {
        setSuccessMsg("Company registered successfully!");
        setFormData({
          companyName: "",
          email: "",
          password: "",
          industry: "",
          location: "",
          website: "",
          description: "",
          status: "active"
        });
        setErrors({});
      } else {
        setSuccessMsg(data.message || "Failed to register company");
      }
    } catch (error) {
      console.error("Error:", error);
      setSuccessMsg("An error occurred while registering the company");
    }
  };

  return (
    <div className={styles.container}>
      <img src={logo} alt="Company Logo" className={styles["company-logo"]} />
      <h1 className={styles.mainheading}><span className={styles.highlight1}>Career</span><span className={styles.highlight2}>Via</span></h1>
      <h3 className={styles.subheading}>Company Registration</h3>
      <p className={styles.paragraph}>Please fill out the form below to register your company.</p>
      {successMsg && <p className={styles.success}>{successMsg}</p>}
      <form onSubmit={handleSubmit} className={styles.formgroup}>
        <div className="col-md-12">
          <label htmlFor="inputCompanyName" className={styles.formlabel}>Company Name:</label>
        <input
          type="text"
          name="companyName"
          id="inputCompanyName"
          placeholder="Tech Solutions pvt ltd"
          value={formData.companyName}
          onChange={handleChange}
          className="form-control"
        />
        {errors.companyName && <p className="error-message">{errors.companyName}</p>}
        </div>

        
        <div className="col-md-12">
          <label htmlFor="inputwebsite" className={styles.formlabel}>Company Website:</label>
            <input
          type="text"
          name="website"
          id="inputwebsite"
          placeholder="https://www.techsolutions.com"
          value={formData.website}
          onChange={handleChange}
          className="form-control"
        />
        {errors.website && <p className="error-message">{errors.website}</p>}
        </div>

        <div className="col-md-12">
          <label htmlFor="inputlocation" className={styles.formlabel}>Company Location:</label>
          <input
            type="text"
            name="location"
            id="inputlocation"
            placeholder="City, State"
            value={formData.location}
          onChange={handleChange}
          className="form-control"
        />
        {errors.location && <p className="error-message">{errors.location}</p>}
        </div>

        <div className="col-md-12">
          <label htmlFor="inputEmail" className={styles.formlabel}>Email ID:</label>
        <input
          type="email"
          name="email"
          id="inputEmail"
          className="form-control"
          placeholder="email@company.com"
          value={formData.email}
          onChange={handleChange}
        />
        {errors.email && <p className="error-message">{errors.email}</p>}
        </div>

        <div className="col-md-12">
            <label htmlFor="inputPassword" className={styles.formlabel}>Password:</label>
        <input
          type="password"
          name="password"
          id="inputPassword"
          className="form-control"
          placeholder="Password"
          value={formData.password}
          onChange={handleChange}
        />
        {errors.password && <p className="error-message">{errors.password}</p>}
        </div>
        
        <div className="col-md-12">
            <label htmlFor="inputState" className={styles.formlabel}>Industry Type:</label>
        <select
          name="industry"
          id="inputState"
          className="form-control"
          placeholder="Industry Type"
          value={formData.industry}
          onChange={handleChange}
        >
          <option value="">Select Industry</option>
          <option value="technology">Technology</option>
          <option value="finance">Finance</option>
          <option value="healthcare">Healthcare</option>
          <option value="education">Education</option>
          <option value="manufacturing">Manufacturing</option>
          <option value="retail">Retail</option>
          <option value="other">Other</option>
        </select>
        {errors.industry && <p className="error-message">{errors.industry}</p>}
        </div>

        <div className="col-12">
            <label htmlFor="inputDescription" className={styles.formlabel}>Company Description:</label>
        <textarea
          name="description"
          placeholder="Enter company description"
          id="inputDescription"
          className="form-control"
          value={formData.description}
          onChange={handleChange}
        />
        {errors.description && <p className="error-message">{errors.description}</p>}
        </div>

      <div className="col-md-12">
        <label htmlFor="inputStatus" className={styles.formlabel}>Company Status:</label>
        <select name="status" value={formData.status} onChange={handleChange} className="form-control">
          <option value="active">ACTIVE</option>
          <option value="inactive">INACTIVE</option>
        </select>
        {errors.status && <p className="error-message">{errors.status}</p>}
        </div>

        <button type="submit">Register</button>

        <div className="col-6">
            <div className="form-check">
              <input className="form-check-input" type="checkbox" id="gridCheck"></input>
              <label className={styles.formchecklabel} htmlFor="gridCheck">
                Already have an account? <Link to="/company-signin">Sign in</Link>
              </label>
            </div>
          </div>
      </form>
    </div>
  );

    }


export default CompanyRegistrationPage;