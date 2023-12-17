class Menu {
	constructor(_name, _activateID, _fa) {
		this.name = _name;
		this.activateID = _activateID;
		this.fa = _fa;
	}

	getHTMLElement() {
		const element = document.createElement("div");
		element.className = "menu";
		const p = document.createElement("p");
		const iT = document.createElement("i");
		iT.className = this.fa;
		p.appendChild(iT);
		p.innerHTML += ` ${this.name}`;
		const i = document.createElement("i");
		i.className = "fa-solid fa-arrow-right";
		element.appendChild(p);
		element.appendChild(i);
		return element;
	}
}

const menus = [];
const pages = [];

function activeMenu() {
	menus.push(new Menu("Upload", "/", "fa-solid fa-hdd"));
	menus.push(new Menu("Multi-Up", "/settings", "fa-solid fa-server"));
	menus.push(new Menu("Compress", "/info/about", "fa-solid fa-file-archive "));

	pages.push(document.getElementById("upload"));
	pages.push(document.getElementById("upmul"));
	pages.push(document.getElementById("compress"));
}

function displayMenu() {
	const sidebarElement = document.getElementById("sidebar");
	const sidebarMenus = sidebarElement.querySelectorAll(":scope > .menu");
	sidebarMenus.forEach((menu) => menu.remove());

	menus.forEach((menu, i) => {
		const element = menu.getHTMLElement();
		sidebarElement.appendChild(element);
		element.addEventListener("click", () => openPage(i));
	});
}

function openPage(index) {
	pages.forEach((page) => (page.style.display = "none"));
	pages[index].style.display = "flex";
}
function openNav() {
	document.getElementById("sidebar").style.width = "250px";
	document.querySelector("#close").style.display = "block";
	document.getElementById("three").style.display = "none";
	document.querySelector(".title").style.display="block";
}

function closeNav() {
	document.getElementById("sidebar").style.width = "0";
	document.querySelector("#close").style.display = "none";
	document.getElementById("three").style.display = "block";
	document.querySelector(".title").style.display = "none";
}
document.getElementById("three").addEventListener("click", function() {
	openNav();
});
this.window.onresize = function() {
	if (window.innerWidth > 780) {
		openNav();
		document.querySelector("#close").style.display = "none";
	} else {
		closeNav();
	}
};

activeMenu();
displayMenu();
openPage(0);
