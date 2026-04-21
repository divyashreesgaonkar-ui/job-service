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
      <div style={{ maxWidth: "700px", margin: "auto" }}>
        <div className="row text-center justify-content-center">
          <div className="col-md-3">
            <h5>CareerVia</h5>
            <p>Find your dream job easily with us.</p>
          </div>

          <div className="col-md-3">
            <h6>Quick Links</h6>
            <ul className="list-unstyled" style={{ padding: 0 }}>
              <li>Home</li>
              <li>About</li>
              <li>Jobs</li>
              <li>Companies</li>
            </ul>
          </div>

          <div className="col-md-3">
            <h6>Services</h6>
            <ul className="list-unstyled">
              <li>Job Search</li>
              <li>Resume Builder</li>
              <li>Career Guidance</li>
              <li>Support</li>
            </ul>
          </div>

          <div className="col-md-3">
            <h6>Contact</h6>
            <p>Email: support@careervia.com</p>
            <p>Phone: +91 9876543210</p>
          </div>
        </div>

        <hr style={{ borderColor: "#4b5563", marginTop: "30px" }} />

        <p style={{ textAlign: "center", marginTop: "10px" }}>
          © 2026 CareerVia. All rights reserved.
        </p>
      </div>
    </footer>
  );
};

export default Footer;
