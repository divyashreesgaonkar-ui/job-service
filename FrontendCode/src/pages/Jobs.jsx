export default function Jobs() {
  const jobs = [
    { title: "Java Developer", company: "TCS", location: "Bangalore" },
    { title: "Frontend Developer", company: "Infosys", location: "Hyderabad" },
    { title: "Software Engineer", company: "Wipro", location: "Chennai" },
  ];

  return (
    <div>
      <h2 className="text-xl font-semibold mb-4">All Jobs</h2>

      <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-4">
        {jobs.map((job, index) => (
          <div key={index} className="bg-white p-4 rounded-xl shadow">
            <h3 className="font-bold">{job.title}</h3>
            <p className="text-gray-600">{job.company}</p>
            <p className="text-sm text-gray-500">{job.location}</p>

            <button className="mt-3 w-full bg-blue-600 text-white py-2 rounded-lg">
              Apply
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}
