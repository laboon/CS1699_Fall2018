require 'digest'

raise "Enter a string to hash" unless ARGV.size > 0
to_hash = ARGV[0]
hash_value = Digest::SHA256.hexdigest(to_hash)
puts "Hash of '#{to_hash}' is: #{hash_value}"
