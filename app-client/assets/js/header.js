/**
   * Header
   */
const header = document.createElement('template');

header.innerHTML = `
<header id="header" class="header fixed-top d-flex align-items-center row">

  <div class="col-11 d-flex align-items-center justify-content-around">
    <div>
      <a href="/views/index.html" class="logo">
        <span>Web Application for studying the Fourier transform</span>
      </a>
    </div>
    
  </div>

  <nav class="col-1 header-nav ms-auto">
    <ul class="d-flex align-items-center">  
      <li class="nav-item dropdown pe-3">

        <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
        <span class="d-none d-md-block dropdown-toggle ps-2 login-username" ></span>
        </a>

        <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
          <li class="dropdown-header">
            <h6 class="login-username"></h6>
            <span id="role"></span>
          </li>
          <li>
            <a class="dropdown-item d-flex align-items-center" id="logout">
              <i class="bi bi-box-arrow-right"></i>
              <span>Sign Out</span>
            </a>
          </li>

        </ul><!-- End Profile Dropdown Items -->
      </li><!-- End Profile Nav -->

    </ul>
  </nav><!-- End Icons Navigation -->

</header>
`;

/**
* Sidebar
*/

const sidebar = document.createElement('template');
document.body.append(header.content);