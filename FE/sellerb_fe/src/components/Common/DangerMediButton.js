import styled, { css } from "styled-components";

const StyledButton = styled.button`
  ${(p) => p.sizeStyle}

  display: block;
  padding: 0px 10px;
  text-align: center;
  overflow: hidden;
  width: 148px;
  height: 42px;

  border-radius: 0px;
  color: #edefe9;
  background-color: #ff7d68;
  border: 0px none;
  margin-right: 10px;

  transition-duration: 400ms;
  transition-timing-function: ease;
  transition-property: transform;

  &:hover {
    transform: scale(1.04);
    background-color: #923c35;
    cursor: pointer;
  }
`;

export const DangerMediButton = ({ disabled, size, label, onClick }) => {

  return (
    <StyledButton
      disabled={disabled}
      sizeStyle={size}
      onClick={() => onClick()}
    >
      {label}
    </StyledButton>
  );
};
