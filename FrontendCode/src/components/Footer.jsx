const Footer = () => {
  return (
    <footer
      style={{
        width: "100vw",
        marginLeft: "calc(-50vw + 50%)",
        background: "linear-gradient(135deg, #020024, #090979, #000428)",
        color: "white",
        padding: "50px 20px",
      }}
    >
      <div style={{ maxWidth: "1200px", margin: "auto" }}>
        <div className="row text-left justify-content-left" style={{padding: "30px", gap: "150px"}}>
          <div className="col-md-6">
            <b><h1>CareerVia Icodian</h1></b>
            <p>Find your dream job easily with us.</p>
          </div>

          <div className="col-md-6">
            <b><h1>Quick Links</h1></b>
            <ul className="list-unstyled" style={{ padding: 0 }}>
              <li><a href="/">Home</a></li>
              <li><a href="/about">About us</a></li>
              <li><a href="/jobs">Jobs</a></li>
              <li><a href="/companies">Companies</a></li>
            </ul>
          </div>

          <div className="col-md-6">
            <b><h1>Services</h1></b>
            <ul className="list-unstyled">
              <li><a href="/job-search">Job Search</a></li>
              <li><a href="/resume-builder">Resume Builder</a></li>
              <li><a href="/career-guidance">Career Guidance</a></li>
              <li><a href="/support">Support</a></li>
            </ul>
          </div>

          <div className="col-md-6">
            <b><h1>Contact</h1></b>
            <p>Email: support@careervia.com</p>
            <p>Phone: +91 9876543210</p>
          </div>
        </div>

        <hr style={{ borderColor: "#4b5563", marginTop: "30px" }} />

        <p style={{ textAlign: "center", marginTop: "10px" }}>
          © 2026 CareerVia Icodian. All rights reserved.
        </p>
      </div>
    </footer>
  );
};

export default Footer;
