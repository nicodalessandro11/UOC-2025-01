# System and Network Administration - Comprehensive Mind Map

## Chapter 1: Introduction to System and Network Administration

### 1.1 What is Network and System Administration?
- **Definition**: Branch of engineering for operational management of human-computer systems
- **Unique characteristics**: 
  - Addresses technology AND users on equal basis
  - Involves maintaining networks despite user activities
- **Primary goal**: Keep systems running for users to produce work
- **Scope**: Global community of machines and organizations
- **Future evolution**: May become more pure resource administration, but currently demands diverse skills
- **Terminology distinction**: 
  - System administration (Unix/mainframe tradition): Managing individual computers
  - Network administration: Managing infrastructure devices (routers/switches)

### 1.2 Applying Technology in an Environment
- **Key tasks**: Hardware configurations and software systems
- **Hardware constraints**: Power, climate, standards conformity
- **Software requirements**: 
  - Needs hardware and OS infrastructure
  - Must inter-operate in global networks
  - Faces compatibility challenges
- **Environment role**: Technology applied for specific purposes
- **Global view**: Systems exposed to both accidental and malicious attacks

### 1.3 The Human Role in Systems
- **Skills required**: 
  - Technical knowledge
  - Patience and understanding  
  - Doctor, psychologist, mechanic roles
  - Organizational abilities
  - Professional attitude
  - Adaptability and continuous learning

### 1.4 Ethical Issues
- **Foundation**: Computer systems are human-computer communities
- **Responsibilities**: 
  - Primary to the greater network community
  - Secondary to users of the system
  - Making objective decisions for productivity/cost
  - Protecting rights of individuals
- **Goal**: Make users' lives bearable, empower them to produce work

### 1.5 Is System Administration a Discipline?
- **Scientific status**: 
  - Lacks systematic experimental data for empirical rigor
  - Can potentially follow scientific form
  - Easier than software engineering to separate human/objective concerns
- **State of practice**: Varies from haphazard to state-of-the-art
- **Academic recognition**: Growing formal discipline with scientific education

### 1.6 Challenges of System Administration
- **Core functions**:
  - Designing logical, efficient networks
  - Deploying easily upgradable machines
  - Deciding needed services
  - Planning and implementing security
  - Providing comfortable user environment
  - Developing error fixing methods
  - Managing growing knowledge
- **Internationalization challenges**:
  - Keyboard variations
  - Font requirements
  - Translation of program messages

### 1.7 Common Practice and Good Practice
- **Critical approach**: Think for yourself, informed skepticism
- **Idea adoption reasons**:
  - Copied without thinking (tradition)
  - Expert consensus on best solution
  - Arbitrary convention
- **Examples**: Driving conventions, emergency numbers, building safety rules
- **Advice**: Make logical rather than obedient choices

### 1.8 Bugs and Emergent Phenomena
- **Sources**:
  - Poor quality control
  - OS and subsystem problems
  - Software incompatibilities
  - Unexplainable phenomena (cosmic rays, viruses)
- **Reality**: Must be prepared to work around uncertainties

### 1.9 Meta Principles
- **Principle 1**: Policy is the foundation (decisions about what we want/should be)
- **Principle 2**: Predictability is the highest aim (basis of reliability, trust, security)
- **Principle 3**: Scalability (systems that grow according to policy)
- **Limits**: Human-computer systems have inherent unpredictability

### 1.10-1.12 Knowledge and Learning
- **Knowledge nature**: Constantly changing, requires continual learning
- **Student approach**:
  - Self-sufficiency in learning
  - Systematic work practices
  - Altruistic system view
  - Balance fatalism with determination
- **Counterfactuals to avoid**:
  - Believing every problem has one right answer
  - Getting upset when things don't work
  - Expecting all problems have clear beginnings/endings
- **Information sources**:
  - Manuals and documentation
  - Unix man pages
  - Web resources
  - RFCs
  - Newsgroups
  - SAGE/Usenix conferences
- **Road-maps in book**:
  - Resource management
  - Human management
  - IP networking
  - System analysis
  - Security

## Chapter 13: Server Hardware Strategies

### Server Definition and Categories
- **Definition**: Machines providing services to clients
- **Categories**:
  - Enterprise: Commercial applications, reliability emphasis
  - Cluster: Distributed computing applications, stripped-down reliability

### 13.1 All Eggs in One Basket Strategy
- **Concept**: One machine for many services
- **Requirements**:
  - Top-tier hardware with expansion ability
  - RAID storage for data integrity
  - Regular backups
- **Limitations**:
  - Single point of failure
  - OS upgrade difficulties
  - Dependency hell for applications
  - Scheduling downtime challenges

### 13.2 Beautiful Snowflakes Strategy
- **Concept**: Separate machine for each service
- **Benefits**: Optimized for specific applications
- **Challenges**:
  - Management burden increases with each unique machine
  - Support overhead grows with variation
  - Asset tracking becomes critical
- **Management approaches**:
  - Asset tracking systems
  - Reducing variations through defaults
  - Supporting limited generations
  - Global optimization vs. local optimization

### 13.3 Buy in Bulk, Allocate Fractions Strategy
- **Concept**: Large servers divided through virtualization
- **Benefits**:
  - Fast VM creation
  - Resource resizing flexibility
  - Reduced stranded capacity
  - Better isolation than multitasking
- **VM Management**:
  - Self-service creation systems
  - Resource limits
  - Standard VM sizes
  - Live migration capabilities
  - VM packing considerations
  - Spare capacity for maintenance (N+1)
- **Containers**: Process-level isolation, lighter weight than VMs

### 13.4 Grid Computing
- **Concept**: Many similar machines managed as one unit
- **Characteristics**:
  - Identical hardware/software configuration
  - Jobs submitted to scheduler
  - Resource allocation algorithms
  - Optimized for batch processing
  - Cost efficiency at scale

### 13.5 Blade Servers
- **Concept**: Multiple servers in specialized chassis
- **Benefits**:
  - Quick installation
  - Software configurable connections
  - Space efficiency
- **Challenges**: Long-term chassis compatibility

### 13.6 Cloud-Based Computing
- **Concept**: Renting computing capacity from providers
- **Definitions vary**:
  - Consumer: Remote data storage
  - Business: Elastic infrastructure
  - IT: Infrastructure managed by others
- **Benefits**:
  - Economies of scale
  - Elasticity for opportunities
  - On-demand resources
- **Options**:
  - SaaS: Web-hosted applications
  - Legal/technical considerations

### 13.7-13.9 Other Options and Summary
- **Server appliances**: Purpose-built devices for specific services
- **Hybrid strategies**: Combination of multiple approaches
- **Selection factors**: Company size, growth expectations, flexibility needs

## Chapter 14: Server Hardware Features

### 14.1 Workstations vs. Servers
- **Server hardware differences**:
  - More CPU performance
  - High-performance I/O
  - Greater expandability
  - Upgrade options
  - Rack mountability
  - Front/rear access
  - High-availability options
  - Remote management capabilities
- **Server OS differences**:
  - Server-specific editions
  - Stripped-down functionality
  - Service-oriented configuration
  - Different patching schedules

### 14.2 Server Reliability
- **N+1 redundancy**: System survives single component failure
- **Data integrity**:
  - RAID levels for disk protection
  - Non-RAID approaches for distributed systems
  - Backups for catastrophic protection
- **Hot-swap components**:
  - Runtime replacement capability
  - Performance during replacement
  - Hot-plug vs. hot-swap distinction
- **Computer room requirements**:
  - Controlled environment
  - Proper power/cooling

### 14.3 Remote Management
- **Out-of-band management**:
  - Integrated (iLO, IPMI, etc.)
  - Security considerations
- **Non-integrated options**:
  - Remote power control
  - IP-KVM access
  - Serial console systems
  - Console concentrators

### 14.4 Administrative Networks
- **Purpose**:
  - Separating administrative traffic
  - Isolate disruptive operations (backups)
  - More stable than service networks
  - Restrictive security policies
- **Design**: Simple, reliable, separate equipment

### 14.5 Maintenance and Support
- **Vendor SLAs**:
  - Response time definitions
  - Service options
  - On-site vs. remote support
- **Spare parts strategies**:
  - Self-support options
  - On-site stock
- **Contract management**:
  - Tracking systems
  - Cross-shipping capabilities
  - Contract renewals

### 14.6-14.7 Vendor Selection
- **Experience factors**: Server design history
- **Component quality**: Premium vs. consumer grade
- **Standards compliance**: MIL-SPEC considerations

## Chapter 15: Server Hardware Specifications

### 15.1 Product Lines
- **Value line**: Lowest initial cost, limited expandability
- **TCO focus**: Long-term cost saving features
- **Performance**: Access to cutting-edge technology
- **Specialty lines**: Vertical market solutions

### 15.2 Hardware Components
- **CPUs**:
  - Core quantity vs. speed tradeoffs
  - Multi-CPU and multi-core considerations
  - Application-specific selection
  - Multitasking vs. multithreading
  - Architecture options (x86-64, ARM)
- **Memory**:
  - System requirements
  - Cache levels (L1, L2, L3, L4)
  - NUMA architecture
  - Swap space considerations
- **Network interfaces**:
  - Bandwidth needs
  - Multiple NICs for different networks
  - NIC bonding for bandwidth/redundancy
- **Storage**:
  - Hardware vs. software RAID
  - Performance characteristics
  - Reliability requirements
- **Power supplies**:
  - N+1 redundancy
  - Multiple power sources
  - Separate power cords

### 15.3-15.4 Configuration Decisions
- **Components to avoid**: Unnecessary peripherals
- **Aesthetic considerations**: Function over form
- **Selection process**: Application requirements guide decisions
