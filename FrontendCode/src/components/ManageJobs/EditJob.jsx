import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import styles from "./EditJob.module.css";

const EditJob = () => {
    const { jobId } = useParams();

    const navigate = useNavigate();

    const [job, setJob] = useState({
        jobTitle: "",
        description: "",
        location: "",
        experience: "",
        salary: "",
        jobType: "",
        requiredSkills: "",
        postedDate: "",
        companyId: "",
        jobStatus: "",
    });

    // Fetch existing job details
    useEffect(() => {
        fetch(`http://localhost:8030/api/jobs/${jobId}`)
            .then((response) => response.json())
            .then((data) => setJob(data))
            .catch((error) => console.error("Error fetching job details:", error));
    }, [jobId]);

    // Handle form input changes
    const handleChange = (e) => {
        setJob({ ...job, [e.target.name]: e.target.value });
    };

    // Update job details
    const handleUpdateJob = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch(`http://localhost:8030/api/jobs/${jobId}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(job),
            });

            if (response.ok) {
                alert("Job updated successfully!");
                navigate("/company-jobs"); // Redirect to job management page after successful update
            }
        } catch (error) {
            console.error("Error updating job:", error);
        }
        };    

        return (
        <div className={styles.editJobContainer}>
            <h2>Edit Job</h2>
            <form onSubmit={handleUpdateJob} className={styles.editJobForm}>

                <input
                    type="text"
                    name="jobTitle"
                    value={job.jobTitle}
                    onChange={handleChange}
                    placeholder="Job Title"
                    required
                />

                <textarea
                    type="text"
                    name="description"
                    value={job.description}
                    onChange={handleChange}
                    placeholder="Job Description"
                    required
                />

                <input
                    type="text"
                    name="location"
                    value={job.location}
                    onChange={handleChange}
                    placeholder="Job Location"
                    required
                />

                <input
                    type="text"
                    name="experience"
                    value={job.experience}
                    onChange={handleChange}
                    placeholder="Required Experience"
                    required
                />

                <input
                    type="text"
                    name="salary"
                    value={job.salary}
                    onChange={handleChange}
                    placeholder="Salary"
                    required
                />

                <input
                    type="text"
                    name="jobType"
                    value={job.jobType}
                    onChange={handleChange}
                    placeholder="Job Type"
                    required
                />

                <input
                    type="text"
                    name="requiredSkills"
                    value={job.requiredSkills}
                    onChange={handleChange}
                    placeholder="Required Skills"
                    required
                />

                <input
                    type="text"
                    name="postedDate"
                    value={job.postedDate}
                    onChange={handleChange}
                    placeholder="Posted Date"
                    required
                />

                <input
                    type="text"
                    name="companyId"
                    value={job.companyId}
                    onChange={handleChange}
                    placeholder="Company ID"
                    required
                />

                <input
                    type="text"
                    name="jobStatus"
                    value={job.jobStatus}
                    onChange={handleChange}
                    placeholder="Job Status"
                    required
                />

                <button type="submit">Update Job</button>
                
            </form>
            
            </div>
           );
};

export default EditJob; 