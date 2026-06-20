import { BrowserRouter, Routes, Route } from "react-router-dom";

import LandingPage from "./pages/LandingPage";
import CandidateRegister from "./pages/CandidateRegister";
import CompleteProfile from "./pages/CompleteProfile";
import RecruiterRegister from "./pages/RecruiterRegister";
import CompanyRegistrationPage from "./pages/CompanyRegistrationPage";
import Dashboard from "./pages/Dashboard";
import Layout from "./pages/Layout";
import Jobs from "./pages/Jobs";
import Login from "./pages/Login";
// import Header from "./components/Header";
import NotFound from "./components/error/NotFound";
import CompanyDashboard from "./components/CompanyDashbooard/CompanyDashboard";
import JobManagement from "./components/ManageJobs/JobManagement";
import EditJob from "./components/ManageJobs/EditJob";
import Header from "./components/Header";
import Footer from "./components/Footer";


function App() {
  return (
    <div>
      <Header/>
      {/* <div style={{ height: '40px' }}></div> */}

     
      <BrowserRouter>
   
        <Routes>
          <Route path="/" element={<LandingPage />} />

          <Route path="/candidate-register" element={<CandidateRegister />} />

          <Route
            path="/complete-profile/:userId"
            element={<CompleteProfile />}
          />

          <Route path="/recruiter-register" element={<RecruiterRegister />} />

          <Route
            path="/company-register"
            element={<CompanyRegistrationPage />}
          />

          <Route path="/company-dashboard" element={<CompanyDashboard />} />
          <Route path="/company-jobs" element={<JobManagement/>} />
          <Route path="/edit-job/:jobId" element={<EditJob />} />

          <Route path="/login" element={<Login />} />

          <Route path="/" element={<Layout />}>
            <Route path="dashboard" element={<Dashboard />} />
            <Route path="jobs" element={<Jobs />} />
          </Route>

          <Route path="*" element={<NotFound />} />
        </Routes>
      </BrowserRouter>
      <Footer/>
    </div>
  );
}

export default App;
