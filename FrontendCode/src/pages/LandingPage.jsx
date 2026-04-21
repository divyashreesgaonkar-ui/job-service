import { useNavigate } from "react-router-dom";

import "../styles/styles.css";

import logo from "../assets/images/logo.png";
import candidateImg from "../assets/images/group.png";
import recruiterImg from "../assets/images/recruitment.png";
import adminImg from "../assets/images/admin.png";
import Footer from "../components/Footer";

function LandingPage() {
  const navigate = useNavigate();

  return (
    <>
      <div className="container">
        {/*Logo*/}
        <div className="header">
          <img src={logo} className="logo" alt="CareerVia Logo" />
          <h1 className="title">CareerVia</h1>
          <p className="tagline">Your Career Your Way</p>
        </div>

        {/*Cards*/}
        <div className="card-container">
          {/* Candidate */}
          <div className="card candidate">
            <div className="card-icon">
              <img src={candidateImg} alt="Candidate" />
            </div>
            <h2>Candidate</h2>
            <h4>LEARN • COMPETE • SUCCEED</h4>
            <p>
              Gamified Learning platform with skills, challenges and courses to
              upgrade yourself
            </p>
            <ul>
              <li>XP & Level System</li>
              <li>Skill Games</li>
              <li>Courses</li>
            </ul>
            <button onClick={() => navigate("/candidate-register")}>
              Register as Candidate
            </button>
          </div>
          {/*Recruiter*/}
          <div className="card recruiter">
            <div className="card-icon">
              <img src={recruiterImg} alt="Recruiter" />
            </div>

            <h2>Recruiter</h2>
            <h4>DISCOVER • EVALUATE • HIRE</h4>

            <p>
              Post jobs, evaluate candidates based on their skills & courses and
              build an excepitional team
            </p>

            <ul>
              <li>Smart Job Posting</li>
              <li>Match the Candidates Accordingly</li>
              <li>Team Analytics</li>
            </ul>
            <button onClick={() => navigate("/recruiter-register")}>
              Register as Recruiter
            </button>
          </div>
          {/*Admin*/}
          <div className="card admin">
            <div className="card-icon">
              <img src={adminImg} alt="Admin" />
            </div>

            <h2>Admin</h2>
            <h4>MONITOR • CONTROL • OPTIMIZE</h4>

            <p>
              Comprehensive System Control, Company approvals and platform
              analytics
            </p>

            <ul>
              <li>System Control</li>
              <li>Approval Center</li>
              <li>Analytics</li>
            </ul>
            <button onClick={() => navigate("/company-register")}>
              Register as Admin
            </button>
          </div>
        </div>

        <footer>
          <p className="footer-text">Already have an account?</p>
          <button onClick={() => navigate("/login")} className="signin-btn">
            SignIn to System
          </button>
        </footer>
      </div>
      <Footer />
    </>
  );
}

export default LandingPage;
