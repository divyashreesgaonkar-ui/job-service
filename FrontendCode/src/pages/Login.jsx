import React, {useState} from "react";
import "../styles/Login.css";
import { Link } from "react-router-dom";
import logo from "../assets/images/logo.png";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
    role: "JOB_SEEKER", // Default role
  });

  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError("");

    try {
      const url = formData.role === "COMPANY"
      ? "http://localhost:8081/api/companies/login"
       : "http://localhost:8082/api/users/login";

       const response = await fetch(url, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          email: formData.email,
          password: formData.password,
        }),
      });

      const data = await response.json();
      console.log("Backend response:", data);

      if (!response.ok) {
        setError(data.message || "Invalid credentials");
        return;
      }

      sessionStorage.setItem("companyId", data.companyId);
      sessionStorage.setItem("role", data.role);
      sessionStorage.setItem("email", data.email);
      sessionStorage.setItem("password", data.password);
      sessionStorage.setItem("companyName", data.companyName);

      

      if(data.role === "COMPANY"){
        navigate("/company-dashboard");
      }
      else if(data.role === "HR"){
        navigate("/hr-dashboard");
      }
      else if(data.role === "JOB_SEEKER"){
        navigate("/dashboard");
      }
    } catch (err) {
      setError("An error occurred. Please try again.");
    } finally {
      setLoading(false);
    }
  };


  return (
    <div className="login-container">
      <div className="login-card">
        {/* <div className="logo">
          <img src={logo} alt="Company Logo" className="logo-img" />
        </div>
        <div className="heading">
          <h1>
            <b>
              Career<span>Via</span>
            </b>
          </h1>
        </div> */}

        <div className="title">
          <h2>Welcome Back</h2>
          <p>Sign in to continue to your account</p>
        </div>

        {error && <p style={{ color: "red" }}>{error}</p>}

        <form onSubmit={handleSubmit}>
          <label >Login As</label>
          <select name="role"  className={formData.role? "form-control" : "form-control-select"} value={formData.role} onChange={handleChange}>
            <option value="JOB_SEEKER">Job Seeker</option>
            <option value="HR">HR</option>
            <option value="COMPANY">Company</option>
          </select>

          <label>Email / User Name</label>
          <input type="text" name="email" placeholder="john@example.com" value={formData.email} onChange={handleChange} required />

          <label>Password</label>
          <input type="password" name="password" placeholder="••••••••" value={formData.password} onChange={handleChange} required/>

          <div className="options">
            <label className="remember">
              <input type="checkbox" />
              <span>Remember me</span>
            </label>

            <a href="/change-password">Forgot password?</a>
          </div>

          <button type="submit">Sign In</button>

          {/* NAVIGATION BACK TO REGISTER */}
          <div className="register">
            New user? <Link to="/">Register Now</Link>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Login;
