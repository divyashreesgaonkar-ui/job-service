import React from "react";
import "../styles/Login.css";
import { Link } from "react-router-dom";
import logo from "../assets/images/logo.png";

const Login = () => {
  return (
    <div className="login-container">
      <div className="login-card">
        <div className="logo">
          <img src={logo} alt="Company Logo" className="logo-img" />
        </div>
        <div className="heading">
          <h1>
            <b>
              Career<span>Via</span>
            </b>
          </h1>
        </div>

        <div className="title">
          <h2>Welcome Back</h2>
          <p>Sign in to continue to your account</p>
        </div>

        <form>
          <label>Email / User Name</label>
          <input type="text" placeholder="john@example.com" />

          <label>Password</label>
          <input type="password" placeholder="••••••••" />

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
