Vagrant.configure("2") do |config|

  config.vm.box = "trusty64"
  config.vm.box_url = "https://cloud-images.ubuntu.com/vagrant/trusty/current/trusty-server-cloudimg-amd64-vagrant-disk1.box"

  config.hostmanager.enabled = true
  config.hostmanager.manage_host = false
  config.hostmanager.manage_guest = true

  config.vm.provider "virtualbox" do |v|
    v.memory = 4096
    v.cpus = 2
  end

  config.vm.define "master" do |master|
    master.vm.hostname = "master"

    master.vm.network :forwarded_port, guest: 80, host: 8080, auto_correct: true
    master.vm.network :forwarded_port, guest: 50000, host: 50000, auto_correct: true
    master.vm.network :private_network, ip: '10.0.0.2'

    master.vm.provision :ansible do |ansible|
       ansible.galaxy_role_file = 'requirements.yml'
       ansible.playbook = "jenkins-master.yml"
    end
  end

end
