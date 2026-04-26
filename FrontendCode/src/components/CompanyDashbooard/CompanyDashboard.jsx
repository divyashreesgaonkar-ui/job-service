import React, {useState, useEffect, use} from "react";
import styles from "./CompanyDashboard.module.css"

const CompanyDashboard = () => {
    const [companyData, setCompanyData] = useState(
        {
            activeJobs: 0,
            totalApplicants: 0,
            pendingApplications: 0,
            rejectedApplications: 0,
            acceptedApplications: 0,
            interviewsScheduled: 0,
            interviewList: [],
        }
    );


useEffect(() => {
    // Fetch company data from API
    fetch("/api/company/dashboard")
        .then((response) => response.json())
        .then((data) => setCompanyData(data))
        .catch((error) => console.error("Error fetching company data:", error));
}, []);

return (
    <div className={styles.dashboardContainer}>
        
        {/* Sidebar */}
        <div className={styles.sidebar}>
            <h2>CareerVia</h2>
            <ul>
                <li><a href="/company-dashboard">Dashboard</a></li>
                <li><a href="/company-jobs">Manage Jobs</a></li>
                <li><a href="/company-applicants">Manage Applicants</a></li>
                <li><a href="/company-interviews">Manage Interviews</a></li>
                <li><a href="/company-settings">Settings</a></li>
                <li><a href="/company-profile">Profile</a></li>
                <li><a href="/company-logout">Logout</a></li>
                <li><a href="/company-help">Help</a></li>
                <li><a href="/company-feedback">Feedback</a></li>
                <li><a href="/company-support">Support</a></li>
            </ul>
        </div>

        {/* Main Content */}
        <div className={styles.mainContent}>
            
            {/* Header */}
            <div className={styles.header}>
                <h3>Welcome to Company Dashboard</h3>
                <div className="profile">
                    <img src="/profile-pic.jpg" alt="Profile" className={styles.profilePic} />
                    <span>Company Name</span>
                </div>
            </div>

            {/* Dashboard Cards */}
            <div className={styles.cards}>
                <div className={styles.card}>
                    <h4>No. of Active Jobs</h4>
                    <p>{companyData.activeJobs}</p>
                </div>

                <div className={styles.card}>
                    <h4>No. of Total Applicants</h4>
                    <p>{companyData.totalApplicants}</p>
                </div>

                <div className={styles.card}>
                    <h4>No. of Pending Applications</h4>
                    <p>{companyData.pendingApplications}</p>
                </div>

                <div className={styles.card}>
                    <h4>No. of Rejected Applications</h4>
                    <p>{companyData.rejectedApplications}</p>
                </div>

                <div className={styles.card}>
                    <h4>No. of Accepted Applications</h4>
                    <p>{companyData.acceptedApplications}</p>
                </div>

                <div className={styles.card}>
                    <h4>No. of Interviews Scheduled</h4>
                    <p>{companyData.interviewsScheduled}</p>
                </div>
            </div>

            {/* Interview List */}
            <div className={styles.tableContainer}>
                <h4>Upcoming Interviews</h4>

                <table>
                    <thead>
                        <tr>
                            <th>Candidate Name</th>
                            <th>Position</th>
                            <th>Date</th>
                            <th>Status</th>
                            <th>email</th>
                        </tr>
                    </thead>

                    <tbody>
                        {companyData.interviewList.map((interview, index) => (
                            <tr key={index}>
                                <td>{interview.candidateName}</td>
                                <td>{interview.position}</td>
                                <td>{interview.date}</td>
                                <td>{interview.status}</td>
                                <td>{interview.email}</td>
                            </tr>
                        ))}
                    </tbody>

                </table>
        </div>
    </div>
    </div>


);
};


export default CompanyDashboard;



