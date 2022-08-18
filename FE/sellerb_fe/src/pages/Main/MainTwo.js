import "./MainTwo.scss";
import { useNavigate } from "react-router-dom";
import { RightCircleOutlined, DoubleRightOutlined } from "@ant-design/icons";

function MainTwo() {
  const navigate = useNavigate();

  return (
    <>
      <div class='main-two-container'>
        <svg viewBox='-250 -200 500 500' version='1.1'>
          <title>@WebDesignerMag</title>
          <g stroke='none' fill-rule='evenodd'>
            <text
              id='@WebDesignerMag'
              stroke='#324E66'
              font-weight='normal'
              font-family='twayair'
              font-size='144'
            >
              <tspan x='-250' y='109'>
                <tspan>S</tspan>
                <tspan>e</tspan>
                <tspan>l</tspan>
                <tspan>l</tspan>
                <tspan>e</tspan>
                <tspan>r</tspan>
                <tspan>B</tspan>
              </tspan>
            </text>
          </g>
        </svg>
        <div className='blink' onClick={() => navigate("/main")}>
          <DoubleRightOutlined style={{ width: "100px", marginTop: "25px" }} />
        </div>
      </div>
    </>
  );
}

export default MainTwo;
