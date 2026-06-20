import React, { useState, useEffect } from "react";
import styles from "./JobManagement.module.css";
import { useNavigate } from "react-router-dom";

const JobManagement = () => {
    const [jobs, setJobs] = useState([]);
    const [form, setForm] = useState(
        {
            jobTitle:"",
            description: "",
            location: "",
            experience: "",
            salary: "",
            jobType: "",
            requiredSkills: "",
            postedDate: "",
            companyId: "",
            jobStatus: "",
        }
    );

    const [searchJobId, setSearchJobId] = useState("");
    const [searchPostedDate, setSearchPostedDate] = useState("");

    const [searchFilters, setSearchFilters] = useState({
        jobTitle: "",
        location: "",
        jobType: "",
        experience: "",
        salary: "",
    });

    // Fetch jobs from API
    useEffect(() => {
        fetch("http://localhost:8030/api/jobs")
            .then((response) => response.json())
            .then((data) => setJobs(data))
            .catch((error) => console.error("Error fetching jobs:", error));
    }, []);

    // Handle form input changes
    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    // Add jobs
    const handleAddJob = async (e) => {
        e.preventDefault();
        console.log("Button clicked");

        try {
            const response = await fetch("http://localhost:8030/api/jobs", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    ...form,
                    requiredSkills: form.requiredSkills
                    .split(",")
                    .map((skill) => skill.trim()), // Convert comma-separated string to array
                }),
            });

            const newJob = await response.json();
            setJobs([...jobs, newJob]);
            setForm({
                jobTitle:"",
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
        }catch (error) {
            console.error("Error adding job:", error);
        }
    };

    // Edit jobs
    /*const handleEditJob = (jobId) => {
        // Implement edit functionality
        const jobToEdit = jobs.find((job) => job.jobId === jobId);
        if (jobToEdit) {
            setForm(jobToEdit);
        }

        // After editing, send PUT request to update job
        try {
            fetch(`http://localhost:8030/api/jobs/${jobId}`, {
                method: "PATCH",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    ...form,
                    requiredSkills: form.requiredSkills
                    .split(",")
                    .map((skill) => skill.trim()), // Convert comma-separated string to array
                }),
            });
        } catch (error) {
            console.error("Error updating job:", error);
        }
    };    */

     const navigate = useNavigate();

    // Delete jobs
    const handleDeleteJob = async (jobId) => {
        try {
            await fetch(`http://localhost:8030/api/jobs/${jobId}`, {
                method: "DELETE",
            });
            setJobs(jobs.filter((job) => job.jobId !== jobId));
        } catch (error) {
            console.error("Error deleting job:", error);
        }
    };

    const handleSearchById = async() => {
        if(!searchJobId) {
            alert("Please enter a Job ID to search");
            return;
        }
        try {
            const response = await fetch(`http://localhost:8030/api/jobs/${searchJobId}`);
            if(!response.ok) {
                throw new Error("Job not found");
            }
            const data = await response.json();
            setJobs([data]);
        }
        catch (error) {
            console.error("Error searching job by ID:", error);
            alert("Job not found");
        }
    };

    const handleSearchByPostedDate = async() => {
        if(!searchPostedDate) {
            alert("Please enter a Posted Date to search");
            return;
        }
        try {
            const response = await fetch(`http://localhost:8030/api/jobs?postedDate=${searchPostedDate}`);
            if(!response.ok) {
                throw new Error("No jobs found for the given posted date");
            }
            const data = await response.json();
            setJobs(data);
        }        catch (error) {
            console.error("Error searching jobs by posted date:", error);
            alert("No jobs found for the given posted date");
        }
    };

    const handleSearchJobs = async() => {
        try {
            const queryParams = new URLSearchParams({
                jobTitle: searchFilters.jobTitle,
                location: searchFilters.location,
                jobType: searchFilters.jobType,
                experience: searchFilters.experience,
                salary: searchFilters.salary,
            }
            );
            const response = await fetch(`http://localhost:8030/api/jobs/jobs/search?${queryParams}`);
            if(!response.ok) {
                throw new Error("No jobs found matching the search criteria");
            }
            const data = await response.json();
            setJobs(data);
        } catch (error) {
            console.error("Error searching jobs with filters:", error);
            alert("No jobs found matching the search criteria");
        }
    };

    const handleResetSearch = async() => {

        try {
            const response = await fetch("http://localhost:8030/api/jobs");
            const data = await response.json();
            setJobs(data);
        } catch (error) {
            console.error("Error resetting search:", error);
        }
    };

    return (
        <div className={styles.container}>
            <h2>Manage Jobs</h2>

            {/* Add Job Form */}
            <form className={styles.jobForm} onSubmit={handleAddJob}>
                <input
                type="text"
                name="jobTitle"
                placeholder="Job Title"
                value={form.jobTitle}
                onChange={handleChange}
                required
                />

                <input
                type="text"
                name="description"
                placeholder="Job Description"
                value={form.description}
                onChange={handleChange}
                required
                />

                <input
                type="text"
                name="location"
                placeholder="Job Location"
                value={form.location}
                onChange={handleChange}
                required
                />

                <input
                type="text"
                name="experience"
                placeholder="Job Experience"
                value={form.experience}
                onChange={handleChange}
                required
                />

                <input
                type="text"
                name="salary"
                placeholder="Job Salary"
                value={form.salary}
                onChange={handleChange}
                required
                />

                <input
                type="text"
                name="jobType"
                placeholder="Job Type"
                value={form.jobType}
                onChange={handleChange}
                required
                />

                <input
                type="text"
                name="requiredSkills"
                placeholder="Required Skills"
                value={form.requiredSkills}
                onChange={handleChange}
                required
                />

                <input
                type="text"
                name="postedDate"
                placeholder="Posted Date"
                value={form.postedDate}
                onChange={handleChange}
                required
                />

                <input
                type="text"
                name="companyId"
                placeholder="Company ID"
                value={form.companyId}
                onChange={handleChange}
                required
                />

                <input
                type="text"
                name="jobStatus"
                placeholder="Job Status"
                value={form.jobStatus}
                onChange={handleChange}
                required
                />

                <button type="submit">Add Job</button>

            </form>

            <div className={styles.searchContainer}>

                <h3>Search Jobs</h3>

                {/* Search by Job ID */}
                <div className={styles.searchBox}>
                <input
                    type="number"
                    placeholder="Search by Job ID"
                    value={searchJobId}
                    onChange={(e) => setSearchJobId(e.target.value)}
                />

                <button onClick={handleSearchById}>
                    Search By ID
                </button>
                </div>

                {/* Search by Posted Date */}
                <div className={styles.searchBox}>
                <input
                    type="date"
                    value={searchPostedDate}
                    onChange={(e) => setSearchPostedDate(e.target.value)}
                />

                <button onClick={handleSearchByPostedDate}>
                    Search By Date
                </button>
            </div>

            {/* Search with Filters */}
            <div className={styles.filterGrid}>

                <input
                    type="text"
                    placeholder="Job Title"
                    value={searchFilters.jobTitle}
                    onChange={(e) =>
                        setSearchFilters({
                            ...searchFilters,
                            jobTitle: e.target.value,
                        })
                    }
                />

                <input
                    type="text"
                    placeholder="Location"
                    value={searchFilters.location}
                    onChange={(e) =>
                        setSearchFilters({
                            ...searchFilters,
                            location: e.target.value,
                        })
                    }
                />

                <input
                    type="text"
                    placeholder="Job Type"
                    value={searchFilters.jobType}
                    onChange={(e) =>
                        setSearchFilters({
                            ...searchFilters,
                            jobType: e.target.value,
                        })
                    }
                />

                <input
                    type="number"
                    placeholder="Experience"
                    value={searchFilters.experience}
                    onChange={(e) =>
                        setSearchFilters({
                            ...searchFilters,
                            experience: e.target.value,
                        })
                    }
                />

                <input
                    type="number"
                    placeholder="Salary"
                    value={searchFilters.salary}
                    onChange={(e) =>
                        setSearchFilters({
                            ...searchFilters,
                            salary: e.target.value,
                        })
                    }
                />

    </div>

    <button
        className={styles.searchButton}
        onClick={handleSearchJobs}
    >
        Search Jobs
    </button>

    <button onClick={handleResetSearch}>
    Reset
</button>

</div>

            {/* Job List */}
            <table className= {styles.jobTable}>
                <thead>
                    <tr>
                        <th>Job ID</th>
                        <th>Job Title</th>
                        <th>Description</th>
                        <th>Location</th>
                        <th>Experience</th>
                        <th>Salary</th>
                        <th>Job Type</th>
                        <th>Required Skills</th>
                        <th>Posted Date</th>
                        <th>Company ID</th>           
                        <th>Job Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {jobs.length>0 ? (
                        jobs.map((job) => (
                        <tr key={job.jobId}>
                            <td>{job.jobId}</td>
                            <td>{job.jobTitle}</td>
                            <td>{job.description}</td>
                            <td>{job.location}</td>
                            <td>{job.experience}</td>
                            <td>{job.salary}</td>
                            <td>{job.jobType}</td>
                            <td>{job.requiredSkills?.join(', ')}</td>
                            <td>{job.postedDate}</td>
                            <td>{job.companyId}</td>
                            <td>{job.jobStatus}</td>
                            <td>
                                <button onClick={() => navigate(`/edit-job/${job.jobId}`)}>Edit</button>
                                <button onClick={() => handleDeleteJob(job.jobId)}>Delete</button>
                            </td>
                        </tr>
                    ))
                ) : (
                    <tr>
                        <td colSpan="8">No jobs available</td>
                    </tr>
                )}
                </tbody>
            </table>

            </div>

    )

};
export default JobManagement;