const cells = document.querySelectorAll(".cell");
  const statusText = document.getElementById("status");

  let currentPlayer = "X";
  let gameActive = true;
  let board = ["", "", "", "", "", "", "", "", ""];

  const winPatterns = [
    [0,1,2],
    [3,4,5],
    [6,7,8],
    [0,3,6],
    [1,4,7],
    [2,5,8],
    [0,4,8],
    [2,4,6]
  ];

  cells.forEach(cell => {
    cell.addEventListener("click", handleClick);
  });

  function handleClick(e) {
    const index = e.target.dataset.index;

    if (board[index] !== "" || !gameActive) return;

    board[index] = currentPlayer;
    e.target.textContent = currentPlayer;
e.target.classList.add(currentPlayer);

const winningPattern = checkWin();

if (winningPattern) {
  statusText.textContent = "Vyhrál: " + currentPlayer;
  document.body.classList.add("win-" + currentPlayer);

  // 🔥 zvýraznění vítězných polí
  winningPattern.forEach((index, i) => {
  cells[index].classList.add("winner-" + currentPlayer);
  cells[index].style.animationDelay = (i * 0.3) + "s";

  });

  gameActive = false;
  return;
}
if (!board.includes("")) {
  statusText.textContent = "Remíza!";
  document.body.classList.add("draw");
  gameActive = false;
  return;
}

    currentPlayer = currentPlayer === "X" ? "O" : "X";
    statusText.textContent = "Hraje: " + currentPlayer;
  }

function checkWin() {
  for (let pattern of winPatterns) {
    if (pattern.every(index => board[index] === currentPlayer)) {
      return pattern; // vrátí vítězné pole
    }
  }
  return null;
}



  function restartGame() {
  board = ["", "", "", "", "", "", "", "", ""];
  gameActive = true;
  currentPlayer = "X";
  statusText.textContent = "Hraje: X";

  document.body.classList.remove("win-X", "win-O", "draw");

  cells.forEach(cell => {
    cell.textContent = "";
    cell.classList.remove("X", "O");

    cells.forEach(cell => {
  cell.textContent = "";
  cell.classList.remove("X", "O", "winner");
  cells.forEach(cell => {
  cell.textContent = "";
  cell.classList.remove("X", "O", "winner-X", "winner-O");
  cell.style.animationDelay = "";
});
});
  });
}
