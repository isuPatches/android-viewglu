desc "Setup the project dependencies"
task :setup do
  # Add the Git Hooks for the project
  Rake::Task[:message].reenable
  Rake::Task[:message].invoke("Setting up .githooks 🚨.")
  Rake::Task[:setup_githooks].invoke
end

desc "Setup .githooks"
task :setup_githooks do
  HOOKS = {
    :pre_commit => "pre-commit",
    :pre_push => "pre-push",
  }

  begin
    system("git config core.hooksPath .githooks")
    HOOKS.each_pair do |key, value|
      system("chmod a+x .githooks/#{value}")
    end
  rescue Exception => e
    Rake::Task[:error].invoke("#{e.message}")
    exit 1
  end
end

desc "Print a message to the command line"
task :message, [:value] do |t, args|
  puts "#{args[:value]}".green
end

desc "Print an error message to the command line"
task :error, [:value] do |t, args|
  puts "❌ #{args[:value]}".red
end

class String
  # colorization
  def colorize(color_code)
    "\e[#{color_code}m#{self}\e[0m"
  end

  def red
    colorize(31)
  end

  def green
    colorize(32)
  end
end
