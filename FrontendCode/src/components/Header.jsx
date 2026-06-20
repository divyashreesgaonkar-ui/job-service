import logo from "../assets/images/logo.png";

const Header = () => {
  return (
    <nav style={{ 
      display: 'flex', 
      alignItems: 'center', 
      justifyContent: 'space-between',
      backgroundColor: 'black', 
      width: '100vw', 
      padding: '12px 40px 12px 100px',
      boxSizing: 'border-box',
    
    }}>
      
      {/* Left - Logo + Tagline */}
      <div style={{ display: 'flex', alignItems: 'center', gap: '16px' }}>
        <a href="/">
          <img src={logo} alt="CareerVia Logo" style={{ height: '70px', width: 'auto', objectFit: 'contain', maxWidth: '180px' }} />
        </a>
        <span style={{ color: '#00e5ff', fontWeight: 'normal', fontSize: '1.2rem', whiteSpace: 'nowrap' }}>
          Your Career Your Way
        </span>
      </div>

      {/* Right - Nav Links + Login Button */}
      <div style={{ display: 'flex', alignItems: 'center', gap: '32px' }}>
        <a href="/" style={{ color: 'white', textDecoration: 'none', fontSize: '1rem' }}>Home</a>
        <a href="/features" style={{ color: 'white', textDecoration: 'none', fontSize: '1rem' }}>Features</a>
        <a href="/about" style={{ color: 'white', textDecoration: 'none', fontSize: '1rem' }}>About</a>
        <a href="/contact" style={{ color: 'white', textDecoration: 'none', fontSize: '1rem' }}>Contact</a>
        <a href="/login" style={{ textDecoration: 'none' }}>
          <button style={{
            background: 'linear-gradient(to right, #00c6ff, #a855f7)',
            color: 'white',
            border: 'none',
            borderRadius: '25px',
            padding: '10px 28px',
            fontSize: '1rem',
            fontWeight: 'bold',
            cursor: 'pointer'
          }}>
            Login
          </button>
        </a>
      </div>

    </nav>
  );
};


export default Header;