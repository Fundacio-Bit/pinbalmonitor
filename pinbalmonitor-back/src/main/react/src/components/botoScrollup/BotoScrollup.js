import React from "react";
import { useEffect, useState } from "react";
import KeyboardArrowUpIcon from "@mui/icons-material/KeyboardArrowUp";
import "./BotoScrollup.css";
// Get the button:

const BotoScrollup = () => {
  const [visible, setVisible] = useState(false);

  const setVisibiitatBoto = () => {
    const scrolled = document.documentElement.scrollTop;
    scrolled > 20 ? 
      setVisible(true) :
      setVisible(false);
    
  };

  const scrollToTop = () => {
    window.scrollTo({
      top: 0,
      behavior: "smooth",
    });
  };

  window.addEventListener("scroll", setVisibiitatBoto);

  return (
    <button
      id="scroll-up"
      onClick={scrollToTop}
      style={{ display: visible ? "inline" : "none" }}
    >
      <KeyboardArrowUpIcon className="icone-scroll-up"></KeyboardArrowUpIcon>
    </button>
  );
};

export default BotoScrollup;
