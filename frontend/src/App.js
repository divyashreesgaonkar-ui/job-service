// import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import NotFound from "./component/error/NotFound";
import Footer from "./component/Footer";
import Header from "./component/Header";
import CompanyRegistrationPage from "./component/home/CompanyRegistrationPage";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";




// const App = () => { return (
//   <CompanyRegistrationPage/>  
// )}

function App() {
  return (
    <div>
      <Header />
        <Router>
          <Routes>
            
            <Route path="/company-register" element={<CompanyRegistrationPage />} />


            <Route path="*" element={<NotFound/>} />


          </Routes>
        </Router>
    
      <Footer />
    </div>
    
  );
}


export default App;
