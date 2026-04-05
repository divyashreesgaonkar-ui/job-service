const Footer = () => {
  return (
    
    <footer style={{ backgroundColor: "#1f2937", color: "white", padding: "40px 20px" }}>
      
      <div className="container">
        <div className="row">

          {/* About */}
          <div className="col-md-3">
            <h5>JobPortal</h5>
            <p>Find your dream job easily with us.</p>
          </div>

          {/* Quick Links */}
          <div className="col-md-3">
            <h6>Quick Links</h6>
            <ul className="list-unstyled">
              <li>Home</li>
              <li>About</li>
              <li>Jobs</li>
              <li>Companies</li>
            </ul>
          </div>

          {/* Services */}
          <div className="col-md-3">
            <h6>Services</h6>
            <ul className="list-unstyled">
              <li>Job Search</li>
              <li>Resume Builder</li>
              <li>Career Guidance</li>
              <li>Support</li>
            </ul>
          </div>

          {/* Contact */}
          <div className="col-md-3">
            <h6>Contact</h6>
            <p>Email: support@jobportal.com</p>
            <p>Phone: +91 9876543210</p>
          </div>

        </div>

        <hr style={{ borderColor: "#4b5563" }} />

        <p style={{ textAlign: "center" }}>
          © 2026 JobPortal. All rights reserved.
        </p>
      </div>

    </footer>
  );
};

export default Footer;